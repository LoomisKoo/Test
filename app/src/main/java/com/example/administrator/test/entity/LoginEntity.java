package com.example.administrator.test.entity;

import java.util.List;

/**
 * @author jingbin
 * @data 2018/5/7
 * @Description
 */

public class LoginEntity {


    /**
     * data : {"chapterTops":[],"collectIds":[],"email":"","icon":"","id":15921,"password":"","token":"","type":0,"username":"286305974@qq.com"}
     * errorCode : 0
     * errorMsg :
     */

    private UserEntity data;
    private int        errorCode;
    private String     errorMsg;

    public UserEntity getData() {
        return data;
    }

    public void setData(UserEntity data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class UserEntity {
        /**
         * chapterTops : []
         * collectIds : []
         * email :
         * icon :
         * id : 15921
         * password :
         * token :
         * type : 0
         * username : 286305974@qq.com
         */

        private String       email;
        private String       icon;
        private int          id;
        private String       password;
        private String       token;
        private int          type;
        private String       username;
        private List<String> chapterTops;
        private List<String>   collectIds;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(List<String> chapterTops) {
            this.chapterTops = chapterTops;
        }

        public List<?> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<String> collectIds) {
            this.collectIds = collectIds;
        }
    }
}
