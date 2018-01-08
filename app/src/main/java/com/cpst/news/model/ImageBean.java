package com.cpst.news.model;

import java.util.List;

/**
 * 图片
 */

public class ImageBean {

    /**
     * article_genre : gallery
     * behot_time : 1515198763
     * gallary_image_count : 7
     * group_id : 6507442212618371592
     * has_gallery : true
     * hot : 1
     * image_list : [{"height":506,"pc_url":"http://p3.pstatp.com/list/300x210/5443000eeeca6ead9c8f","uri":"list/640x360/5443000eeeca6ead9c8f","url":"http://p3.pstatp.com/list/640x360/5443000eeeca6ead9c8f","url_list":[{"url":"http://p3.pstatp.com/list/640x360/5443000eeeca6ead9c8f"},{"url":"http://pb9.pstatp.com/list/640x360/5443000eeeca6ead9c8f"},{"url":"http://pb1.pstatp.com/list/640x360/5443000eeeca6ead9c8f"}],"width":900},{"height":531,"pc_url":"http://p3.pstatp.com/list/300x210/56660004155b107b3b2b","uri":"list/56660004155b107b3b2b","url":"http://p3.pstatp.com/list/640x360/56660004155b107b3b2b","url_list":[{"url":"http://p3.pstatp.com/list/56660004155b107b3b2b"},{"url":"http://pb9.pstatp.com/list/56660004155b107b3b2b"},{"url":"http://pb1.pstatp.com/list/56660004155b107b3b2b"}],"width":900},{"height":506,"pc_url":"http://p3.pstatp.com/list/300x210/544200140ee6a5b9afea","uri":"list/544200140ee6a5b9afea","url":"http://p3.pstatp.com/list/640x360/544200140ee6a5b9afea","url_list":[{"url":"http://p3.pstatp.com/list/544200140ee6a5b9afea"},{"url":"http://pb9.pstatp.com/list/544200140ee6a5b9afea"},{"url":"http://pb1.pstatp.com/list/544200140ee6a5b9afea"}],"width":900}]
     * image_url : http://p3.pstatp.com/list/640x360/5443000eeeca6ead9c8f
     * is_diversion_page : false
     * is_feed_ad : false
     * media_avatar_url : http://p3.pstatp.com/large/bc200009ef51d8e07d0
     * media_url : http://toutiao.com/m50043246607/
     * middle_mode : false
     * more_mode : true
     * single_mode : false
     * source : 人民图片
     * source_url : /group/6507442212618371592/
     * title : 江苏扬州：“小寒”节气 航拍雪野银装素裹美如画
     */

    private String article_genre;
    private int behot_time;
    private int gallary_image_count;
    private String group_id;
    private boolean has_gallery;
    private int hot;
    private String image_url;
    private boolean is_diversion_page;
    private boolean is_feed_ad;
    private String media_avatar_url;
    private String media_url;
    private boolean middle_mode;
    private boolean more_mode;
    private boolean single_mode;
    private String source;
    private String source_url;
    private String title;
    private List<ImageListBean> image_list;

    public String getArticle_genre() {
        return article_genre;
    }

    public void setArticle_genre(String article_genre) {
        this.article_genre = article_genre;
    }

    public int getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(int behot_time) {
        this.behot_time = behot_time;
    }

    public int getGallary_image_count() {
        return gallary_image_count;
    }

    public void setGallary_image_count(int gallary_image_count) {
        this.gallary_image_count = gallary_image_count;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public boolean isHas_gallery() {
        return has_gallery;
    }

    public void setHas_gallery(boolean has_gallery) {
        this.has_gallery = has_gallery;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isIs_diversion_page() {
        return is_diversion_page;
    }

    public void setIs_diversion_page(boolean is_diversion_page) {
        this.is_diversion_page = is_diversion_page;
    }

    public boolean isIs_feed_ad() {
        return is_feed_ad;
    }

    public void setIs_feed_ad(boolean is_feed_ad) {
        this.is_feed_ad = is_feed_ad;
    }

    public String getMedia_avatar_url() {
        return media_avatar_url;
    }

    public void setMedia_avatar_url(String media_avatar_url) {
        this.media_avatar_url = media_avatar_url;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public boolean isMiddle_mode() {
        return middle_mode;
    }

    public void setMiddle_mode(boolean middle_mode) {
        this.middle_mode = middle_mode;
    }

    public boolean isMore_mode() {
        return more_mode;
    }

    public void setMore_mode(boolean more_mode) {
        this.more_mode = more_mode;
    }

    public boolean isSingle_mode() {
        return single_mode;
    }

    public void setSingle_mode(boolean single_mode) {
        this.single_mode = single_mode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImageListBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<ImageListBean> image_list) {
        this.image_list = image_list;
    }

    public static class ImageListBean {
        /**
         * height : 506
         * pc_url : http://p3.pstatp.com/list/300x210/5443000eeeca6ead9c8f
         * uri : list/640x360/5443000eeeca6ead9c8f
         * url : http://p3.pstatp.com/list/640x360/5443000eeeca6ead9c8f
         * url_list : [{"url":"http://p3.pstatp.com/list/640x360/5443000eeeca6ead9c8f"},{"url":"http://pb9.pstatp.com/list/640x360/5443000eeeca6ead9c8f"},{"url":"http://pb1.pstatp.com/list/640x360/5443000eeeca6ead9c8f"}]
         * width : 900
         */

        private int height;
        private String pc_url;
        private String uri;
        private String url;
        private int width;
        private List<UrlListBean> url_list;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getPc_url() {
            return pc_url;
        }

        public void setPc_url(String pc_url) {
            this.pc_url = pc_url;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public List<UrlListBean> getUrl_list() {
            return url_list;
        }

        public void setUrl_list(List<UrlListBean> url_list) {
            this.url_list = url_list;
        }

        public static class UrlListBean {
            /**
             * url : http://p3.pstatp.com/list/640x360/5443000eeeca6ead9c8f
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}