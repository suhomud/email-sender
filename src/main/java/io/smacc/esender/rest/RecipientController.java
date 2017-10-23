package io.smacc.esender.rest;

import io.smacc.esender.domain.Recipient;
import io.smacc.esender.domain.RecipientWeb;
import io.smacc.esender.service.RecipientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipient")
@Api(tags = {"recipient"})
public class RecipientController {

	private final RecipientService recipientService;

	@Autowired
	public RecipientController(RecipientService recipientService) {
		this.recipientService = recipientService;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public RecipientWeb getAll() {
		return new RecipientWeb(recipientService.getAll());
	}

	@RequestMapping(
			method = RequestMethod.POST,
			produces = {"application/json"})
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Recipient recipient) {
		recipientService.add(recipient);
	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			produces = {"application/json"})
	@ResponseStatus(HttpStatus.CREATED)
	public void clear() {
		recipientService.clear();
	}

}
