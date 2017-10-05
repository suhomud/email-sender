package io.smacc.esender.rest;

import io.smacc.esender.Email;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipient")
public class RecipientController {

	@RequestMapping(value = "",
			method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Email> getAllRecipients(
			HttpServletRequest request, HttpServletResponse response) {
		return new ArrayList<>();
	}

}
