package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.List;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class SourceMapData {

  List<ReceiveSourceMapFile> receiveSourceMapFiles;

  @Data
  public class ReceiveSourceMapFile {
    private String fieldname;

    private String originalname;

    private String encoding;

    private String mimetype;

    private String destination;

    private String filename;

    private String path;

    private Integer size;

  }

}
