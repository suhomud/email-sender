package io.smacc.esender.rest;

import io.smacc.esender.domain.Message;
import io.smacc.esender.service.SenderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sender")
@Api(tags = {"sender"})
public class SenderController {

	private final SenderService senderService;

	@Autowired
	public SenderController(SenderService senderService) {
		this.senderService = senderService;
	}

	@RequestMapping(
			method = RequestMethod.POST,
			produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void send(@RequestBody Message message) throws Exception {
		senderService.send(message);
	}

}
