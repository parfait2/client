package com.example.client.dto;

public class Req<T> {

    /*
    {
        "header" : {
            "response_code" : "OK"
        },
        "body" : {
            "name" : "spring boot",
            "age" : 1024
        }
    }
    형태의 json 받기
    */

    private Header header;
    private T rBody;

    public static class Header {
        private String responseCode;

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "responseCode='" + responseCode + '\'' +
                    '}';
        }
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getrBody() {
        return rBody;
    }

    public void setrBody(T rBody) {
        this.rBody = rBody;
    }

    @Override
    public String toString() {
        return "Req{" +
                "header=" + header +
                ", body=" + rBody +
                '}';
    }
}
