package com.office.qljt.qljtoffice.utils;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RequestVerify {



    @SerializedName("obj")
    private Obj obj;
    @SerializedName("stamp")
    private Long stamp;

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public Long getStamp() {
        return stamp;
    }

    public void setStamp(Long stamp) {
        this.stamp = stamp;
    }

    @NoArgsConstructor
    @Data
    public class Obj {


        @SerializedName("params")
        private Object params;
        @SerializedName("url")
        private String url;
        @SerializedName("method")
        private String method;
        @SerializedName("o_t_th_f_fi_ran")
        private String oTThFFiRan;
        @SerializedName("rule")
        private List<Integer> rule;

        public Object getParams() {
            return params;
        }

        public void setParams(Object params) {
            this.params = params;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getOTThFFiRan() {
            return oTThFFiRan;
        }

        public int[] getOTThFFiRanArray() {
            String thFFiRan = getOTThFFiRan();
            int[] oTThFFiRanArray = stringConvertInt(thFFiRan);
            return oTThFFiRanArray;
        }

        private int[] stringConvertInt(String value) {
            int[] intArr = new int[0];
            if (TextUtils.isEmpty(value)) {
                intArr = new int[0];
            } else {
                String[] valueArr = value.split("\\.");
                intArr = new int[valueArr.length];
                for (int i = 0; i < valueArr.length; i++) {
                    intArr[i] = Integer.parseInt(valueArr[i]);
                }
            }
            return intArr;
        }

        public void setoTThFFiRan(String oTThFFiRan) {
            this.oTThFFiRan = oTThFFiRan;
        }

        public List<Integer> getRule() {
            return rule;
        }

        public void setRule(List<Integer> rule) {
            this.rule = rule;
        }
    }
}
