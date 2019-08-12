package com.example.cache_data;


import java.io.Serializable;
import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/5/30 16:54
 */

public class AdvertBean implements Serializable{

    /**
     * result : 1
     * message : null
     * data : {"adDetails":{"acid":24,"cname":"麻雀e疗App首页轮播","width":"700px","height":"350px","description":null,"anumber":null,"atype":0,"rule":null,"userid":null,"siteid":null,"disabled":"false"},"advList":[{"aid":51,"acid":24,"atype":0,"begintime":0,"endtime":0,"isclose":0,"attachment":null,"atturl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16581650.jpg","url":"./taocandetails.html","aname":"入职体检","clickcount":0,"disabled":"false","linkman":null,"company":null,"contact":null,"cname":"","httpAttUrl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16581650.jpg"},{"aid":53,"acid":24,"atype":0,"begintime":0,"endtime":0,"isclose":0,"attachment":null,"atturl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16490758.jpg","url":"../product/productlist.html","aname":"贴针灸","clickcount":0,"disabled":"false","linkman":null,"company":null,"contact":null,"cname":"","httpAttUrl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16490758.jpg"},{"aid":54,"acid":24,"atype":0,"begintime":0,"endtime":0,"isclose":0,"attachment":null,"atturl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16345000.jpg","url":"../product/zhongliulist.html","aname":"Watson","clickcount":0,"disabled":"false","linkman":null,"company":null,"contact":null,"cname":"","httpAttUrl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16345000.jpg"},{"aid":55,"acid":24,"atype":0,"begintime":0,"endtime":0,"isclose":0,"attachment":null,"atturl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/8/15//54471562.jpg","url":"../friend/invitation.html","aname":"邀请好友","clickcount":0,"disabled":"false","linkman":null,"company":null,"contact":null,"cname":"","httpAttUrl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/8/15//54471562.jpg"}]}
     */

    private int result;
    private Object message;
    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * adDetails : {"acid":24,"cname":"麻雀e疗App首页轮播","width":"700px","height":"350px","description":null,"anumber":null,"atype":0,"rule":null,"userid":null,"siteid":null,"disabled":"false"}
         * advList : [{"aid":51,"acid":24,"atype":0,"begintime":0,"endtime":0,"isclose":0,"attachment":null,"atturl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16581650.jpg","url":"./taocandetails.html","aname":"入职体检","clickcount":0,"disabled":"false","linkman":null,"company":null,"contact":null,"cname":"","httpAttUrl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16581650.jpg"},{"aid":53,"acid":24,"atype":0,"begintime":0,"endtime":0,"isclose":0,"attachment":null,"atturl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16490758.jpg","url":"../product/productlist.html","aname":"贴针灸","clickcount":0,"disabled":"false","linkman":null,"company":null,"contact":null,"cname":"","httpAttUrl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16490758.jpg"},{"aid":54,"acid":24,"atype":0,"begintime":0,"endtime":0,"isclose":0,"attachment":null,"atturl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16345000.jpg","url":"../product/zhongliulist.html","aname":"Watson","clickcount":0,"disabled":"false","linkman":null,"company":null,"contact":null,"cname":"","httpAttUrl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16345000.jpg"},{"aid":55,"acid":24,"atype":0,"begintime":0,"endtime":0,"isclose":0,"attachment":null,"atturl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/8/15//54471562.jpg","url":"../friend/invitation.html","aname":"邀请好友","clickcount":0,"disabled":"false","linkman":null,"company":null,"contact":null,"cname":"","httpAttUrl":"https://www.maqueezu.com/statics/attachment/adv/2018/12/8/15//54471562.jpg"}]
         */

        private AdDetailsBean adDetails;
        private List<AdvListBean> advList;

        public AdDetailsBean getAdDetails() {
            return adDetails;
        }

        public void setAdDetails(AdDetailsBean adDetails) {
            this.adDetails = adDetails;
        }

        public List<AdvListBean> getAdvList() {
            return advList;
        }

        public void setAdvList(List<AdvListBean> advList) {
            this.advList = advList;
        }

        public static class AdDetailsBean implements Serializable {
            /**
             * acid : 24
             * cname : 麻雀e疗App首页轮播
             * width : 700px
             * height : 350px
             * description : null
             * anumber : null
             * atype : 0
             * rule : null
             * userid : null
             * siteid : null
             * disabled : false
             */

            private int acid;
            private String cname;
            private String width;
            private String height;
            private Object description;
            private Object anumber;
            private int atype;
            private Object rule;
            private Object userid;
            private Object siteid;
            private String disabled;

            public int getAcid() {
                return acid;
            }

            public void setAcid(int acid) {
                this.acid = acid;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public Object getAnumber() {
                return anumber;
            }

            public void setAnumber(Object anumber) {
                this.anumber = anumber;
            }

            public int getAtype() {
                return atype;
            }

            public void setAtype(int atype) {
                this.atype = atype;
            }

            public Object getRule() {
                return rule;
            }

            public void setRule(Object rule) {
                this.rule = rule;
            }

            public Object getUserid() {
                return userid;
            }

            public void setUserid(Object userid) {
                this.userid = userid;
            }

            public Object getSiteid() {
                return siteid;
            }

            public void setSiteid(Object siteid) {
                this.siteid = siteid;
            }

            public String getDisabled() {
                return disabled;
            }

            public void setDisabled(String disabled) {
                this.disabled = disabled;
            }
        }

        public static class AdvListBean implements Serializable {
            /**
             * aid : 51
             * acid : 24
             * atype : 0
             * begintime : 0
             * endtime : 0
             * isclose : 0
             * attachment : null
             * atturl : https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16581650.jpg
             * url : ./taocandetails.html
             * aname : 入职体检
             * clickcount : 0
             * disabled : false
             * linkman : null
             * company : null
             * contact : null
             * cname :
             * httpAttUrl : https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16581650.jpg
             */

            private int aid;
            private int acid;
            private int atype;
            private int begintime;
            private int endtime;
            private int isclose;
            private Object attachment;
            private String atturl;
            private String url;
            private String aname;
            private int clickcount;
            private String disabled;
            private Object linkman;
            private Object company;
            private Object contact;
            private String cname;
            private String httpAttUrl;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public int getAcid() {
                return acid;
            }

            public void setAcid(int acid) {
                this.acid = acid;
            }

            public int getAtype() {
                return atype;
            }

            public void setAtype(int atype) {
                this.atype = atype;
            }

            public int getBegintime() {
                return begintime;
            }

            public void setBegintime(int begintime) {
                this.begintime = begintime;
            }

            public int getEndtime() {
                return endtime;
            }

            public void setEndtime(int endtime) {
                this.endtime = endtime;
            }

            public int getIsclose() {
                return isclose;
            }

            public void setIsclose(int isclose) {
                this.isclose = isclose;
            }

            public Object getAttachment() {
                return attachment;
            }

            public void setAttachment(Object attachment) {
                this.attachment = attachment;
            }

            public String getAtturl() {
                return atturl;
            }

            public void setAtturl(String atturl) {
                this.atturl = atturl;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAname() {
                return aname;
            }

            public void setAname(String aname) {
                this.aname = aname;
            }

            public int getClickcount() {
                return clickcount;
            }

            public void setClickcount(int clickcount) {
                this.clickcount = clickcount;
            }

            public String getDisabled() {
                return disabled;
            }

            public void setDisabled(String disabled) {
                this.disabled = disabled;
            }

            public Object getLinkman() {
                return linkman;
            }

            public void setLinkman(Object linkman) {
                this.linkman = linkman;
            }

            public Object getCompany() {
                return company;
            }

            public void setCompany(Object company) {
                this.company = company;
            }

            public Object getContact() {
                return contact;
            }

            public void setContact(Object contact) {
                this.contact = contact;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public String getHttpAttUrl() {
                return httpAttUrl;
            }

            public void setHttpAttUrl(String httpAttUrl) {
                this.httpAttUrl = httpAttUrl;
            }
        }
    }
}
