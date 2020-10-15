package controllers;

import java.util.ArrayList;

import model.CreditCard;
import model.PrepaidSystem;
import view.FramePrepaidSystem;
import view.FrameWelcome;

public class PrepaidSystemController {
	private FramePrepaidSystem view;
	private FrameWelcome initialView;
	private PrepaidSystem model;
	
	public PrepaidSystemController(FramePrepaidSystem view,PrepaidSystem model,FrameWelcome initialView)
	{
		this.view=view;
		this.model=model;
		this.initialView=initialView;
	}
	

	public PrepaidSystemController() {
		model = new PrepaidSystem();
		view = new FramePrepaidSystem(this,new ArrayList<CreditCard>());
		

	}

	public void setView(FramePrepaidSystem view) {
		this.view = view;

	}
	
	public void setInitialView(FrameWelcome initialView) {
		this.initialView = initialView;

	}
	
	
	
	

}
