package com.office.qljt.qljtoffice.handler;

import com.office.qljt.qljtoffice.dao.SduDao;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.SduDTO;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.User;
import com.office.qljt.qljtoffice.service.UserService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.office.qljt.qljtoffice.constant.CommonConst.*;

/**
 * @author 续加仪
 * @date 2022/10/7
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {


    @Autowired
    private SduDao sduDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @Autowired
    private IdWorker idWorker;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String uid = request.getHeader("u");
        if (!TextUtils.isEmpty(uid)) {
            uid = UriEncoder.decode(uid);
        }
        String sduId = authentication.getName();
        if (!TextUtils.isEmpty(sduId)) {
            sduId = UriEncoder.decode(sduId);
        }
        String sduName = (String) authentication.getCredentials();
        if (!TextUtils.isEmpty(sduName)) {
            sduName = UriEncoder.decode(sduName);
        }
        // 查询账号是否存在
        UserDTO userDTO = userDao.getUserDTOByIdAndSduId(uid, sduId);
        SduDTO sduDTO;

        if (userDTO == null) {
            sduDTO = sduDao.getSduDTO(sduId);
            userDTO = convertUserDTO(sduDTO, uid);
        } else {
            sduDTO = userDTO.getSduInfo();
        }

        if (sduDTO == null) {
            throw new BadCredentialsException("用户不存在!");
        }
        if (!sduDTO.getSduName().equals(sduName)) {
            throw new BadCredentialsException("用户名或密码错误!");
        }
        userService.saveUserInfoToRedis(userDTO);

        return new UsernamePasswordAuthenticationToken(userDTO, authentication.getCredentials(), AuthorityUtils.commaSeparatedStringToAuthorityList(userDTO.getRole() + ""));

    }

    /**
     * 封装用户登录信息
     *
     * @param sduDTO 用户账号
     * @param uid    uid
     * @return 用户登录信息
     */
    public UserDTO convertUserDTO(SduDTO sduDTO, String uid) {
        if (sduDTO == null) return null;
        // 查询账号信息
        UserDTO userDTO = userDao.getUserDTOBySduId(sduDTO.getId());
        if (userDTO == null) {
            String openid = TextUtils.isEmpty(uid) ? idWorker.nextId() + "" : uid;
            User user = User.builder()
                    .userAvatar(USER_AVATAR)
                    .userNickname(USER_NICK_NAME)
//                    .userCity(USER_COUNTRY)
//                    .userCity(USER_CITY)
//                    .userProvince(USER_PROVINCE)
//                    .userLanguage(USER_LANGUAGE)
//                    .userCountry(USER_COUNTRY)
                    .sduId(sduDTO.getId())
                    .status(1L)
                    .role(0L)
                    .id(openid)
                    .build();
            userService.saveOrUpdate(user);
            userDTO = BeanCopyUtils.copyObject(user, UserDTO.class);
            userDTO.setSduInfo(sduDTO);
        }
        return userDTO;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
