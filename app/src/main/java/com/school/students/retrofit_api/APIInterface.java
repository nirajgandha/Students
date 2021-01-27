package com.school.students.retrofit_api;

import com.school.students.model.*;

import okhttp3.*;
import retrofit2.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface APIInterface {

    @FormUrlEncoded
    @POST(ServerConfig.LOGIN_API)
    Call<LoginResponse> loginApi(@Field("phone") String phone,
                        @Field("device_type") String device_type,
                        @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST(ServerConfig.OTP_API)
    Call<LoginResponse> verifyOtpApi(@Field("phone") String phone,
                                 @Field("otp") String otp);

    @FormUrlEncoded
    @POST(ServerConfig.GET_STUDENT_LIST_API)
    Call<GetStudentListResponse> getStudentListApi(@Field("phone") String phone);

    @FormUrlEncoded
    @POST(ServerConfig.SELECT_STUDENT_API)
    Call<SelectStudentResponse> selectStudentApi(@Field("phone") String phone,
                                                  @Field("student_id") String student_id);

    @FormUrlEncoded
    @POST(ServerConfig.HOME_WORK_LIST_API)
    Call<GetHomeworkResponse> getHomeWorkListApi(@Field("class_id") String class_id,
                                                   @Field("section_id") String section_id,
                                                  @Field("student_id") String student_id);
    @FormUrlEncoded
    @POST(ServerConfig.GET_ATTENDANCE_LIST_API)
    Call<AttendanceListResponse> getAttendanceListApi(@Field("class_id") String class_id,
                                                   @Field("section_id") String section_id,
                                                   @Field("student_id") String student_id,
                                                   @Field("current_year") String current_year);

    @FormUrlEncoded
    @POST(ServerConfig.GET_SYLLABUS_LIST_API)
    Call<GetSyllabusResponse> getSyllabusListApi(@Field("class_id") String class_id,
                                                   @Field("section_id") String section_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_NOTICE_LIST_API)
    Call<NoticeResponse> getNoticeListApi(@Field("student_id") String student_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_FOOD_MENU_LIST_API)
    Call<FoodMenuResponse> getFoodMenuListApi(@Field("student_id") String student_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_CO_CURRICULUM_ACTIVITY_LIST_API)
    Call<CoCurriculumResponse> getCoActivityListApi(@Field("class_id") String class_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_EVENT_LIST_API)
    Call<CalendarResponse> getCalendarListApi(@Field("class_id") String class_id,
                                                      @Field("section_id") String section_id,
                                                      @Field("start_date_of_month") String start_date_of_month);

    @FormUrlEncoded
    @POST(ServerConfig.GET_EVENT_LIST_API)
    Call<CalendarResponse> getCalendarDayListApi(@Field("class_id") String class_id,
                                              @Field("section_id") String section_id,
                                              @Field("selected_date") String selected_date);

    @Multipart
    @POST(ServerConfig.UPLOAD_HOMEWORK_API)
    Call<UploadHomeworkResponse> uploadHomeworkApi(@Part("student_id") RequestBody student_id,
                                             @Part("homework_id") RequestBody homework_id,
                                             @Part MultipartBody.Part[] uploadFiles);

    @FormUrlEncoded
    @POST(ServerConfig.GET_GALLERY_API)
    Call<GalleryResponse> getGalleryListApi(@Field("student_id") String student_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_ACTIVITY_API)
    Call<ToDoActivityResponse> getActivityListApi(@Field("student_id") String student_id);

    @FormUrlEncoded
    @POST(ServerConfig.GET_FEES_API)
    Call<FeeResponse> getFeeListApi(@Field("student_id") String student_id);
}
