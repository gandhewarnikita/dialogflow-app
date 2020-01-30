package com.google.dialogflow.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import com.google.actions.api.response.helperintent.SelectionList;
import com.google.api.services.actions_fulfillment.v2.model.Image;
import com.google.api.services.actions_fulfillment.v2.model.ListSelectListItem;
import com.google.api.services.actions_fulfillment.v2.model.MediaObject;
import com.google.api.services.actions_fulfillment.v2.model.MediaResponse;
import com.google.api.services.actions_fulfillment.v2.model.OptionInfo;

@Service
public class DialogApp extends DialogflowApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(DialogApp.class);

	@ForIntent("new_intent")
	public ActionResponse getReplayAction(ActionRequest request) {
		LOGGER.info("replay_actions");

		ResponseBuilder responseBuilder = getResponseBuilder(request);

	//	LOGGER.info("responsebuilder : " + responseBuilder.toString());

		ActionResponse response = responseBuilder.add("This is a list example.")
				.add(new SelectionList().setTitle("Themes").setItems(Arrays.asList(new ListSelectListItem()
						.setTitle("New Age Technology").setDescription("New Age Technology")
						.setImage(new Image().setUrl(
								"https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
								.setAccessibilityText("NAT"))
						.setOptionInfo(new OptionInfo().setKey("1")),
						new ListSelectListItem().setTitle("Visual Design Thinking")
								.setDescription("Visual Design Thinking")
								.setImage(new Image().setUrl(
										"https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
										.setAccessibilityText("VDT"))
								.setOptionInfo(new OptionInfo().setKey("2")),
						new ListSelectListItem().setTitle("PRo LEarning solutions")
								.setDescription("PRo LEarning solutions")
								.setImage(new Image().setUrl(
										"https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
										.setAccessibilityText("PLT"))
								.setOptionInfo(new OptionInfo().setKey("3")))))
				.build();

		LOGGER.info("response : " + response.toString());

		return response;
	}

//	@ForIntent("New Age Technology")
//	public ActionResponse listSelected(ActionRequest request) {
//
//		LOGGER.info("New Age Technology");
//
//		ResponseBuilder responseBuilder = getResponseBuilder(request);
//
//		responseBuilder.add("This is a list example.")
//				.add(new SelectionList().setTitle("Themes").setItems(Arrays.asList(new ListSelectListItem()
//						.setTitle("Digital Transformation").setDescription("Digital Transformation")
//						.setImage(new Image().setUrl(
//								"https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
//								.setAccessibilityText("DT"))
//						.setOptionInfo(new OptionInfo().setKey("1")),
//						new ListSelectListItem().setTitle("Program Management").setDescription("Program Management")
//								.setImage(new Image().setUrl(
//										"https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
//										.setAccessibilityText("PM"))
//								.setOptionInfo(new OptionInfo().setKey("2")),
//						new ListSelectListItem().setTitle("Change Management").setDescription("Change Management")
//								.setImage(new Image().setUrl(
//										"https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
//										.setAccessibilityText("CM"))
//								.setOptionInfo(new OptionInfo().setKey("3")))));
//		return responseBuilder.build();
//	}
//
//	@ForIntent("New Age Technology - OPTION")
//	public ActionResponse listSelectedItem(ActionRequest request) {
//		
//		LOGGER.info("New Age Technology - OPTION 1 - Digital Transformation");
//		
//		ResponseBuilder responseBuilder = getResponseBuilder(request);
//		String selectedItem = request.getSelectedOption();
//		if (selectedItem.equals("1")) {
//			responseBuilder.add("This is a Digital Transformation media response example.")
//					.add(new MediaResponse().setMediaObjects(new ArrayList<MediaObject>(Arrays.asList(new MediaObject()
//							.setName("Digital Transformation").setDescription("Digital Transformation")
//							.setContentUrl("https://www.youtube.com/watch?v=508CR1fd8ws")
//							.setIcon(new Image().setUrl(
//									"https://media.wired.com/photos/5b8999943667562d3024c321/master/w_2560%2Cc_limit/trash2-01.jpg")
//									.setAccessibilityText("DT")))))
//							.setMediaType("VIDEO"));
//
//		}
//		return responseBuilder.build();
//	}

}
