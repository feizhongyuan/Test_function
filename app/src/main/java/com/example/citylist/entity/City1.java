package com.example.citylist.entity;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/8 17:50
 */

public class City1 {

    private List<CityBean> city;

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    public static class CityBean {
        /**
         * initial : A
         * list : [{"code":"0997","name":"阿克苏","pinyin":"Akesu","label":"Akesu0997"},{"code":"0837","name":"阿坝","pinyin":"Aba","label":"Aba0837"},{"code":"0483","name":"阿拉善盟","pinyin":"Alashanmeng","label":"Alashanmeng0483"},{"code":"0906","name":"阿勒泰","pinyin":"Aletai","label":"Aletai0906"},{"code":"0897","name":"阿里","pinyin":"Ali","label":"Ali0897"},{"code":"0915","name":"安康","pinyin":"Ankang","label":"Ankang0915"},{"code":"0556","name":"安庆","pinyin":"Anqing","label":"Anqing0556"},{"code":"0412","name":"鞍山","pinyin":"Anshan","label":"Anshan0412"},{"code":"0853","name":"安顺","pinyin":"Anshun","label":"Anshun0853"},{"code":"0372","name":"安阳","pinyin":"Anyang","label":"Anyang0372"},{"code":"0451","name":"阿城","pinyin":"Acheng","label":"Acheng0451"},{"code":"0796","name":"安福","pinyin":"Anfu","label":"Anfu0796"},{"code":"0572","name":"安吉","pinyin":"Anji","label":"Anji0572"},{"code":"0871","name":"安宁","pinyin":"Anning","label":"Anning0871"},{"code":"0536","name":"安丘","pinyin":"Anqiu","label":"Anqiu0536"},{"code":"0595","name":"安溪","pinyin":"Anxi","label":"Anxi0595"},{"code":"0791","name":"安义","pinyin":"Anyi","label":"Anyi0791"},{"code":"0797","name":"安远","pinyin":"Anyuan","label":"Anyuan0797"}]
         */

        private String initial;
        private List<ListBean> list;

        public String getInitial() {
            return initial;
        }

        public void setInitial(String initial) {
            this.initial = initial;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * code : 0997
             * name : 阿克苏
             * pinyin : Akesu
             * label : Akesu0997
             */

            private String code;
            private String name;
            private String pinyin;
            private String label;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }
    }
}
