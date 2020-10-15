package view;

import java.awt.EventQueue;

import controllers.PrepaidSystemController;
import model.PrepaidSystem;

public class System {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			PrepaidSystem model = new PrepaidSystem();
			PrepaidSystemController controller = new PrepaidSystemController(null,model,null);
			FrameWelcome initialView=new FrameWelcome(controller);
			controller.setInitialView(initialView);
		});

	}
}
