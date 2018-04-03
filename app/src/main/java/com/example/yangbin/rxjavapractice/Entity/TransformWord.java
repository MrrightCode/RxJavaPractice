package com.example.yangbin.rxjavapractice.Entity;

import java.util.List;

/**
 * Created by yangbin on 2018/3/23.
 */

public class TransformWord {

    /**
     * status : 1
     * content : {"from":"zh-CN","to":"en-US","out":"test","vendor":"ciba","err_no":0}
     */

    private int status;
    private ContentEntity content;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public ContentEntity getContent() {
        return content;
    }

    public static class ContentEntity {
        /**
         * from : zh-CN
         * to : en-US
         * out : test
         * vendor : ciba
         * err_no : 0
         */

        private String from;
        private String to;
        private String out;
        private String vendor;
        private int err_no;
        private String ph_en;
        private String ph_am;
        private String ph_en_mp3;
        private String ph_am_mp3;

        public List<String> getWord_mean() {
            return word_mean;
        }

        public void setWord_mean(List<String> word_mean) {
            this.word_mean = word_mean;
        }

        private List<String> word_mean;

        public String getPh_en() {
            return ph_en;
        }

        public void setPh_en(String ph_en) {
            this.ph_en = ph_en;
        }

        public String getPh_am() {
            return ph_am;
        }

        public void setPh_am(String ph_am) {
            this.ph_am = ph_am;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }

        public void setPh_en_mp3(String ph_en_mp3) {
            this.ph_en_mp3 = ph_en_mp3;
        }

        public String getPh_am_mp3() {
            return ph_am_mp3;
        }

        public void setPh_am_mp3(String ph_am_mp3) {
            this.ph_am_mp3 = ph_am_mp3;
        }

        public String getPh_tts_mp3() {
            return ph_tts_mp3;
        }

        public void setPh_tts_mp3(String ph_tts_mp3) {
            this.ph_tts_mp3 = ph_tts_mp3;
        }



        private String ph_tts_mp3;

        public void setFrom(String from) {
            this.from = from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public void setErr_no(int err_no) {
            this.err_no = err_no;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public String getOut() {
            return out;
        }

        public String getVendor() {
            return vendor;
        }

        public int getErr_no() {
            return err_no;
        }
    }
}
