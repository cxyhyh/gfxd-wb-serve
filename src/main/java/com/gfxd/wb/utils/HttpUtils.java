/**
 * Author: hyh
 * Date: 2022/8/3 13:29
 * Describe:
 */

package com.gfxd.wb.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DatabindContext;
import com.gfxd.wb.entity.dto.DocInfoDto;
import com.gfxd.wb.entity.dto.FinanceVo;
import com.gfxd.wb.entity.model.Finance;
import com.gfxd.wb.entity.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;

/**
 * Author: hyh
 * Date: 2022/8/3 13:29
 * Describe:
 */
@Slf4j
@Component
public class HttpUtils {


    /**
     * POST数据
     * @param apiUrl
     */
    public JSONObject postForMap(String apiUrl, MultiValueMap<String, String> map){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl,
                map, String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
    }

    /**
     * POST数据
     * @param apiUrl
     */
    public void postForFile(String apiUrl, MultiValueMap<String, String> map, String fileName , HttpServletRequest request,HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
        ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(apiUrl,map, byte[].class);
        byte[] body = responseEntity.getBody();
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF8") + ".xls");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setContentType("applicationnd/vnd.ms-excel");
        response.getOutputStream().write(body);
    }

    /**
     * POST数据
     * @param apiUrl
     */
    public void postForDownload(String apiUrl, MultiValueMap<String, String> map,HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/octet-stream;charset=UTF-8");
        ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(apiUrl,map, byte[].class);
        byte[] body = responseEntity.getBody();
        response.setContentType("application/octet-stream");
        response.getOutputStream().write(body);
    }

    /**
     * POST数据
     * @param apiUrl
     */
    public String postForAvatar(String apiUrl, MultiValueMap<String, String> map, HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(apiUrl,map, byte[].class);
        byte[] body = responseEntity.getBody();
        String base64Str = Base64.getEncoder().encodeToString(body);
        return base64Str;
    }

    /**
     * POST数据
     * @param apiUrl
     */
    public HashMap postForFileMap(String apiUrl, HttpEntity<MultiValueMap> requestEntity){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        ResponseEntity<HashMap> responseEntity = restTemplate.postForEntity(apiUrl,
                requestEntity, HashMap.class);
        HashMap map = responseEntity.getBody();
        return map;
    }

    /**
     * POST数据
     * @param apiUrl
     */
    public JSONObject postForLoginUser(String apiUrl, User user){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        org.springframework.http.HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, httpEntity, String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
    }

    /**
     * POST数据
     * @param apiUrl
     */
    public JSONObject postForFinance(String apiUrl, Finance finance){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        org.springframework.http.HttpEntity<Finance> httpEntity = new HttpEntity<>(finance, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, httpEntity, String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
    }

    /**
     * POST数据
     * @param apiUrl
     */
    public JSONObject postForFinanceVo(String apiUrl, FinanceVo financeVo){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        org.springframework.http.HttpEntity<FinanceVo> httpEntity = new HttpEntity<>(financeVo, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, httpEntity, String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
    }

    /**
     * POST数据
     * @param apiUrl
     */
    public JSONObject postForDocInfoDto(String apiUrl, DocInfoDto docInfoDto){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        org.springframework.http.HttpEntity<DocInfoDto> httpEntity = new HttpEntity<>(docInfoDto, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, httpEntity, String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
    }
}