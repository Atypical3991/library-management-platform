package com.example.library_management_platform.models.api.response;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class GetUserProfileResponse extends BaseResponse{

    private GetUserProfileData data;

    @Data
    public class GetUserProfileData{
        private Long id;
        private String firstName;
        private String lastName;
        private String userName;
        private String email;
        private String role;
        private List<ActiveIssuedBooks> activeIssuedBooks;
        private List<InactiveIssuedBooks> inActiveIssuedBooks;
        private List<ReadAuthors> readAuthorsList;
        private List<ReadGenres> readGenresList;
        private List<ReadPublishers> readPublishersList;
    }

    @Data
    public class ActiveIssuedBooks{
        private Long Id;
        private String name;
        private List<String> authors;
        private List<String> genres;
        private String publisher;
        private Date issuedAt;
        private Date issueExpireAt;
        private String status;
    }

    @Data
    public class InactiveIssuedBooks{
        private Long Id;
        private String name;
        private List<String> authors;
        private List<String> genres;
        private String publisher;
        private Date issuedAt;
        private Date issueExpireAt;
        private String status;
    }

    @Data
    public class ReadGenres{
        private Long Id;
        private String name;
    }

    @Data
    public class ReadAuthors{
        private Long Id;
        private String name;
    }

    @Data
    public class ReadPublishers{
        private Long id;
        private String name;
    }

}
