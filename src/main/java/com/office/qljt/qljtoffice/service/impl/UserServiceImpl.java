package com.office.qljt.qljtoffice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SduDao;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.SduDTO;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.User;
import com.office.qljt.qljtoffice.service.UserService;
import com.office.qljt.qljtoffice.utils.*;
import com.office.qljt.qljtoffice.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SduDao sduDao;

    @Autowired
    private RedisServiceImpl redisService;

    @Resource
    private HttpServletRequest request;

    @Override
    public UserDTO getLoginUser() {

        try {
            return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            String from = request.getHeader("from");
            //判断是不是小程序用户
            if (from != null && from.equals("weixin_miniprogram_qljt" + new Date().getDate())) {
                //是
                String uid = request.getHeader("u");
                if (TextUtils.isEmpty(uid)) {

                    String sdu_id = request.getHeader("sdud");

                    if (sdu_id != null) {
                        sdu_id = UriEncoder.decode(sdu_id);
                    }
                    String sdu_name = request.getHeader("sdun");
                    if (sdu_name != null) {
                        sdu_name = UriEncoder.decode(sdu_name);
                    }
                    UserDTO userDTO = userDao.getUserDTOBySduId(sdu_id);
                    if (userDTO != null && userDTO.getSduInfo().getSduName().equals(sdu_name)) {
                        saveUserInfoToRedis(userDTO);
                        return userDTO;
                    }
                } else {
                    return getUserDTOByUserIdFromRedis(UriEncoder.decode(uid));
                }
            }
            return null;
        }
    }

    /**
     * decoding encrypted data to get openid
     *
     * @param iv
     * @param encryptedData
     * @param code
     * @return
     */
    @Override
    public Result<?> decodeUerInfo(String iv, String encryptedData, String code) {
        Map<String, Object> map = new HashMap<>();
        // login code can not be null
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return Result.fail(map);
        }
        // mini-Program's AppID
        String wechatAppId = "wxdf2f5fff3b7fc4a0";
        // mini-Program's session-key
        String wechatSecretKey = "fb0b63eee2474fdcec8c38e6a455a5fd";
        String grantType = "authorization_code";
        // using login code to get sessionId and openId
        String params = "?appid=" + wechatAppId + "&secret=" + wechatSecretKey + "&js_code=" + code + "&grant_type=" + grantType;
        // sending request
        String sr = HttpRequestUtils.doGet("https://api.weixin.qq.com/sns/jscode2session" + params);
        // analysis request content
        JSONObject json = JSONObject.parseObject(sr);
        // getting session_key
        String sessionKey = json.get("session_key").toString();

        // getting open_id
        String openId = json.get("openid").toString();
        // decoding encrypted info with AES
        try {
            String result = DecryptUtils.decryptData(encryptedData, sessionKey, iv);
//            String result = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");
                JSONObject userInfoJSON = JSONObject.parseObject(result);
                map.put("userInfo", userInfoJSON);
                return Result.ok(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return Result.fail(map);
    }

    /**
     * decoding encrypted data to get openid
     *
     * @param code
     * @return
     */
    @Override
    public Result<?> getOpenid(String code) {
        // login code can not be null
        if (code == null || code.length() == 0) {
            return Result.fail("code is null");
        }
        // mini-Program's AppID
        String wechatAppId = "wxdf2f5fff3b7fc4a0";
        // mini-Program's session-key
        String wechatSecretKey = "fb0b63eee2474fdcec8c38e6a455a5fd";
        // using login code to get sessionId and openId
        String params = "?appid=" + wechatAppId + "&secret=" + wechatSecretKey + "&js_code=" + code + "&grant_type=authorization_code";
        // sending request
        String sr = HttpRequestUtils.doGet("https://api.weixin.qq.com/sns/jscode2session" + params);
        // analysis request content
        JSONObject json = JSONObject.parseObject(sr);
        return Result.ok(json);

    }

    @Override
    public UserDTO getLoginUser(HttpServletRequest request) {
        try {
            return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            String from = request.getHeader("from");
            //判断是不是小程序用户
            if (from != null && from.equals("weixin_miniprogram_qljt" + new Date().getDate())) {
                //是
                String uid = request.getHeader("u");
                return getUserDTOByUserIdFromRedis(uid);
            }
            return null;
        }
    }

    @Override
    public UserDTO getUserDTOByUserId(String userId) {
        if (TextUtils.isEmpty(userId)) return null;
        return userDao.getUserDTOByUserId(userId);
    }

    @Override
    public Result<?> saveOrUpdateUser(UserVO userVO) {
        User user = BeanCopyUtils.copyObject(userVO, User.class);
        SduDTO sduDTO = sduDao.getSduDTO(user.getSduId());
        if (sduDTO == null) return Result.fail("查无此人，添加失败");
        if (TextUtils.isEmpty(user.getId())) user.setId(idWorker.nextId() + "");
        if (user.getStatus() == null) user.setStatus(1L);
        this.saveOrUpdate(user);
        redisService.del("login_" + user.getId());
        return Result.ok();
    }

    @Override
    public PageResult<UserDTO> listUsersDTO() {
        return new PageResult<>(userDao.listUsersDTO(PageUtils.getCurrent() * PageUtils.getSize(), PageUtils.getSize()),
                PageUtils.getCurrent(), PageUtils.getSize(), userDao.selectCount(null));
    }
    @Override
    public PageResult<UserDTO> listUsersDTOByCondition(ConditionVO conditionVO) {
        return new PageResult<>(userDao.listUsersDTOByCondition(conditionVO), conditionVO.getCurrent() != null ? conditionVO.getCurrent() : 0L, conditionVO.getSize() != null ? conditionVO.getSize() : userDao.selectCount(null), userDao.selectCount(null));
    }

    @Override
    public PageResult<UserDTO> listAllUsersDTO() {
        return new PageResult<>(userDao.listAllUsersDTO(), userDao.selectCount(null));
    }

    @Override
    public void updateUsersDelete(DeleteVO deleteVO) {
        List<User> userList = deleteVO.getIdList().stream()
                .map(id -> User.builder()
                        .id(id)
                        .status(deleteVO.getStatus())
                        .build()).collect(Collectors.toList());
        this.updateBatchById(userList);
    }

    @Override
    public UserDTO getUserDTOByUserIdFromRedis(String id) {
        UserDTO userDTO = JSONObject.parseObject((String) redisService.get("login_" + id), UserDTO.class);
        if (userDTO == null) {
            userDTO = userDao.getUserDTOByUserId(id);
            saveUserInfoToRedis(userDTO);
        }
        return userDTO;
    }

    @Override
    public void saveUserInfoToRedis(UserDTO userDTO) {
        if (userDTO != null)
            redisService.set("login_" + userDTO.getId(), JSONObject.toJSONString(userDTO), 2 * 60 * 60);
    }
}
