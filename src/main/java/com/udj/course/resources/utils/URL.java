package com.udj.course.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {


    public static String decodeParam(String param){
        return URLDecoder.decode(param, StandardCharsets.UTF_8);
    }

    public static List<Long> decodeList(String s) {
        return Arrays.stream(s.split(",")).map(Long::parseLong).collect(Collectors.toList());
    }

}
