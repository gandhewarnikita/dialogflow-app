package com.google.dialogflow.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.actions.api.ActionResponse;
import com.google.actions.api.App;
import com.google.api.client.json.Json;

@RestController
@RequestMapping(value = "/api/dialog")
public class DialogFlowController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DialogFlowController.class);

	@Value("${GOOGLE_URL}")
	public String Url;

	@Value("${GOOGLE_AUTH_TOKEN}")
	public String GOOGLE_AUTH_TOKEN;

	private final App actionsApp = new DialogApp();

	@RequestMapping(value = "/action", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAction(@RequestHeader Map<String, String> headers)
			throws InterruptedException, ExecutionException, ClientProtocolException, IOException {

		LOGGER.info("in getAction()");

		String payload = "";
		String listPayload = "";
		LOGGER.info("Url : " + Url);

		HttpPost post = new HttpPost(Url);
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Authorization", "Bearer " + GOOGLE_AUTH_TOKEN);

		// payload = "\"{\\\"queryInput\\\":{\\\"text\\\":{\\\"text\\\":\\\"talk to
		// cdc\\\",\\\"languageCode\\\":\\\"en\\\"}},\\\"queryParams\\\":{\\\"timeZone\\\":\\\"Asia\\/Calcutta\\\"}}\"";

		// payload = "{\"queryInput\":{\"text\":{\"text\":\"talk to
		// cdc\",\"languageCode\":\"en\"}},\"queryParams\":{\"timeZone\":\"Asia/Calcutta\"}}\"";

		// payload = "\"{\\\"queryInput\\\":{\\\"text\\\":{\\\"text\\\":\\\"talk to
		// cdc\\\",\\\"languageCode\\\":\\\"en\\\"}},\\\"queryParams\\\":{\\\"timeZone\\\":\\\"Asia\\/Calcutta\\\"}}\"";

		payload = "{\"queryInput\":{\"text\":{\"text\":\"talk to cdc\",\"languageCode\":\"en\"}},\"queryParams\":{\"timeZone\":\"Asia/Calcutta\"}}";
		listPayload = "{\"queryInput\":{\"text\":{\"text\":\"list cdc themes\",\"languageCode\":\"en\"}},\"queryParams\":{\"timeZone\":\"Asia/Calcutta\"}}";

		StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
		post.setEntity(entity);

		LOGGER.info("post entity : " + post.getEntity());

		HttpClient client1 = HttpClientBuilder.create().build();
		HttpResponse response = client1.execute(post);

		LOGGER.info("response : " + response);

		String responseString = new BasicResponseHandler().handleResponse(response);

		LOGGER.info("responseString : " + responseString);

		return responseString;
	}

}
