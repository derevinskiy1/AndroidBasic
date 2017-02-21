package com.example.storm.mvp.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
public class Weather {

    /**
     * boardid : dy_wemedia_bbs
     * clkNum : 0
     * digest :
     * docid : BQ80DHHG05198KH1
     * downTimes : 0
     * id : BQ80DHHG05198KH1
     * img : http://dingyue.nosdn.127.net/hXDr0nCrIw=6FxL9uQoVgxki66e33zvaisdYh1MKxRG7=1466643276158.jpg
     * imgType : 0
     * imgsrc : http://dingyue.nosdn.127.net/hXDr0nCrIw=6FxL9uQoVgxki66e33zvaisdYh1MKxRG7=1466643276158.jpg
     * picCount : 1
     * pixel : 270*202
     * program : HY
     * prompt : 成功为您推荐10条新内容
     * ptime : 2016-06-23 08:54:49
     * recType : -1
     * replyCount : 0
     * replyid : BQ80DHHG05198KH1
     * source : 上海联合金融
     * template : normal
     * title : “偷尾盘” 预示多头何种意图？
     * unlikeReason : ["期货/1","广告/6","来源:上海联合金融/4","内容重复/6"]
     * upTimes : 0
     */

    private List<推荐Entity> 推荐;

    public List<推荐Entity> get推荐() {
        return 推荐;
    }

    public void set推荐(List<推荐Entity> 推荐) {
        this.推荐 = 推荐;
    }

    public static class 推荐Entity {
        private String boardid;
        private int clkNum;
        private String digest;
        private String docid;
        private int downTimes;
        private String id;
        private String img;
        private int imgType;
        private String imgsrc;
        private int picCount;
        private String pixel;
        private String program;
        private String prompt;
        private String ptime;
        private int recType;
        private int replyCount;
        private String replyid;
        private String source;
        private String template;
        private String title;
        private int upTimes;
        private List<String> unlikeReason;

        public String getBoardid() {
            return boardid;
        }

        public void setBoardid(String boardid) {
            this.boardid = boardid;
        }

        public int getClkNum() {
            return clkNum;
        }

        public void setClkNum(int clkNum) {
            this.clkNum = clkNum;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public int getDownTimes() {
            return downTimes;
        }

        public void setDownTimes(int downTimes) {
            this.downTimes = downTimes;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getImgType() {
            return imgType;
        }

        public void setImgType(int imgType) {
            this.imgType = imgType;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public int getPicCount() {
            return picCount;
        }

        public void setPicCount(int picCount) {
            this.picCount = picCount;
        }

        public String getPixel() {
            return pixel;
        }

        public void setPixel(String pixel) {
            this.pixel = pixel;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public int getRecType() {
            return recType;
        }

        public void setRecType(int recType) {
            this.recType = recType;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getReplyid() {
            return replyid;
        }

        public void setReplyid(String replyid) {
            this.replyid = replyid;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUpTimes() {
            return upTimes;
        }

        public void setUpTimes(int upTimes) {
            this.upTimes = upTimes;
        }

        public List<String> getUnlikeReason() {
            return unlikeReason;
        }

        public void setUnlikeReason(List<String> unlikeReason) {
            this.unlikeReason = unlikeReason;
        }
    }
}
