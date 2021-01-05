package com.school.students.retrofit_api;

import com.school.students.model.*;

import retrofit2.*;
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
    Call<StudentListResponse> getStudentListApi(@Field("phone") String phone);

    @FormUrlEncoded
    @POST(ServerConfig.SELECT_STUDENT_API)
    Call<StudentListResponse> selectStudentApi(@Field("phone") String phone,
                                               @Field("student_id") String student_id);

}
