package com.khubla.pragmatach.framework.controller.impl;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.controller.impl.trivial.*;

/**
 * @author tome
 */
@Controller(name = "pragmatachNotFoundController")
public class NotFoundController extends TrivialController {
	public NotFoundController() {
		super(404);
	}
}
