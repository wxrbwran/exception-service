package com.xzlcorp.exception.manager.controller;

import com.xzlcorp.exception.common.model.pojo.event.Event;
import com.xzlcorp.exception.manager.service.impl.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("events")
@Slf4j
public class EventController {

  @Autowired
  private EventService eventService;

  @PostMapping()
  public void handleTransferEvent(@RequestBody Event event) throws NoSuchAlgorithmException {
    log.info("handleTransferEvent event, {}", event);
    eventService.handleEvent(event);
  }
}
