package com.example.administrator.test.entity;

import java.util.List;

public class JokeBean {
    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"一男子跟朋友走在路上，突然发现五个女人正在围殴其丈母娘！　　朋友见状，忙问男子：\u201c你还不赶紧上去帮忙？！\u201d　　男子镇定地说：\u201c不用，五个人足够了！\u201d","hashId":"0a8b6fdd70daa311aa82a771132d9895","unixtime":1487980430,"updatetime":"2017-02-25 07:53:50"}]}
     */

    private int        error_code;
    private String     reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * content : 一男子跟朋友走在路上，突然发现五个女人正在围殴其丈母娘！　　朋友见状，忙问男子：“你还不赶紧上去帮忙？！”　　男子镇定地说：“不用，五个人足够了！”
             * hashId : 0a8b6fdd70daa311aa82a771132d9895
             * unixtime : 1487980430
             * updatetime : 2017-02-25 07:53:50
             */

            private String content;
            private String hashId;
            private int    unixtime;
            private String updatetime;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
