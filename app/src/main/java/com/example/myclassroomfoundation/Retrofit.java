package com.example.myclassroomfoundation;

import com.example.myclassroomfoundation.Teacher.assignmentModel;
import com.example.myclassroomfoundation.models.loginmodel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public class Retrofit {

    public static String url = "http://mcflmsbbb.tech/mcf-lms/api/";
    public static String url2 = "http://mcflmsbbb.tech/mcf-lms/bbb/";

    public static services services = null;
    public static services services1 = null;

    public static services getServices() {

        if (services == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            services = retrofit.create(services.class);
        }
        return services;
    }

    public static services getServicesM() {

        if (services1 == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url2)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            services1 = retrofit.create(services.class);
        }
        return services1;
    }

    public interface services {

        @GET("api.login.php")
        Call<loginmodel> getRole(@Query("email") String email,
                                 @Query("password") String password);

        @GET("api.teacherSubjectList")
        Call<List<assignmentModel>> getSubjects(@Query("contact_id") String id);

        @GET("api.assignmentQuestions")
        Call<List<questionModel>> getQuestions(@Query("subject_id") String subject_id);

        @GET("api.assignmentAnswers")
        Call<List<answerModel>> getAnswers(@Query("subject_id") String id, @Query("question_id") String q_id);

        @GET("api.profile")
        Call<user> getUserDetails(@Query("contact_id") String id);

        @GET("api.studentSubjectList")
        Call<List<assignmentModel>> getStudentSubject(@Query("contact_id") String id);

        @GET("api.classList")
        Call<List<classImodel>> getClasses();

        @GET("api.sectionList")
        Call<List<sectionModel>> getSections(@Query("class_id") String id);

        @GET("api.teacherList")
        Call<List<teacherModel>> getTeachers();

        @GET("api.createAssignments")
        Call<result> createAssignment(@Query("question") String question,
                                      @Query("document") String document,
                                      @Query("marks") String marks,
                                      @Query("subject_id") String subject_id
        );


        @GET("api.assignAssignmentMarks")
        Call<result> assignMarks(@Query("question_id") String id,
                                 @Query("subject_id") String s_id,
                                 @Query("contact_id") String c_id,
                                 @Query("marks") String marks
                                 );


        @GET("api.submitAssignments")
        Call<result> submitAssignments(@Query("subject_id") String s_id,
                                 @Query("answer") String answer,
                                 @Query("document") String document,
                                 @Query("question_id") String q_id,
                                 @Query("contact_id") String c_id
        );


        @GET("api.createSubject")
        Call<result> createSubject(@Query("subject_name") String subject,
                                   @Query("class_id") String id,
                                   @Query("section_id") String s_id,
                                   @Query("teacher_id") String t_id);

        @GET("api.register")
        Call<result> register(@Query("first_name") String f_name,
                              @Query("last_name") String l_name,
                              @Query("email") String email,
                              @Query("password") String password,
                              @Query("date_of_birth") String date_of_birth,
                              @Query("phone_no") String phone_no,
                              @Query("address") String address,
                              @Query("address2") String address2,
                              @Query("city") String city,
                              @Query("state") String state,
                              @Query("country") String country,
                              @Query("pincode") String pincode,
                              @Query("role") String role,
                              @Query("section_id") String section_id,
                              @Query("class_id") String class_id
                              );

        @GET("api.profile")
        Call<user> getProfile(@Query("contact_id") String id);

        @GET("api.joinMeetingAttendeeUrl.php")
        Call<result> joinMeeting(@Query("subject_id") String id,
                                 @Query("contact_id") String c_id);

        @GET("api.joinMeetingModeratorUrl.php")
        Call<result> joinTeacher(@Query("subject_id") String id,
                                 @Query("contact_id") String c_id);

        @GET("api.createMeeting.php")
        Call<result> createMeeting(@Query("meetingName") String name,
                                 @Query("attendeePW") String ap,
                                 @Query("moderatorPW") String mp,
                                 @Query("welcome") String msg,
                                 @Query("subject_id") String id,
                                 @Query("teacher_id") String c_id);

        @GET("api.assignmentReport")
        Call<List<report_result>> assignmentMarks(@Query("subject_id") String id,
                               @Query("contact_id") String c_id);

    }
}
