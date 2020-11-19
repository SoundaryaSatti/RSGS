package com.integro.rsgs.api;

import com.integro.rsgs.model.EducationList;
import com.integro.rsgs.model.LayMissionList;
import com.integro.rsgs.model.LeaderShipList;
import com.integro.rsgs.model.MediaList;
import com.integro.rsgs.model.MinistriesList;
import com.integro.rsgs.model.NewsList;
import com.integro.rsgs.model.NotificationList;
import com.integro.rsgs.model.SocialActionList;
import com.integro.rsgs.model.WhereWeAreList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("rgs_news.php")
    Call<NewsList> getNewsList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("rgs_notification.php")
    Call<NotificationList> getNotificationList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("rgs_mission.php")
    Call<LayMissionList> getLayMissionList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("rgs_education.php")
    Call<EducationList> getEducationList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("rgs_social.php")
    Call<SocialActionList> getSocialActionList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("rgs_where.php")
    Call<WhereWeAreList> getWhereWeAreList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("rgs_leadership.php")
    Call<LeaderShipList> getLeaderShipList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("rgs_ministries.php")
    Call<MinistriesList> getMinistriesList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("rgs_media.php")
    Call<MediaList> getMediaList(@Field("updated_at")String updated_at);



}