package com.example;

import org.apache.pulsar.client.api.schema.GenericRecord;
import org.apache.pulsar.common.schema.KeyValue;
import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;
import org.slf4j.Logger;
import java.util.Date;
import com.google.gson.Gson;

public class ConversionFunction implements Function<KeyValue<GenericRecord, GenericRecord>, String> {
    @Override
    public String process(KeyValue<GenericRecord, GenericRecord> input, Context context) {
        Logger LOG = context.getLogger();

        try{
            int postTypeId = (int) input.getValue().getField("postTypeId");
            String body = (String) input.getValue().getField("body");
            String title = (String) input.getValue().getField("title");
            Date createDate = (Date) input.getValue().getField("creationDate");
            int score = (int) input.getValue().getField("score");
            int viewCount = (int) input.getValue().getField("viewCount");
            int answerCount = (int) input.getValue().getField("answerCount");
            
            SO_Post post = new SO_Post(postTypeId, body, title, score, createDate, viewCount, answerCount);
            String postJson = post.toJson();
            return postJson;
        }catch(Exception ex){
            LOG.error("An error occurred parsing CDC message, \"{}\"", ex.getMessage());
        }

        return null;
    }
}

class SO_Post{
    private int _postTypeId;
    private String _postMessage;
    private String _title;
    private int _score;
    private Date _createDate;
    private int _viewCount;
    private int _answerCount;

    public SO_Post(
        int postTypeId,
        String postMessage,
        String title,
        int score,
        Date creationDate,
        int viewCount,
        int answerCount
    ){
        setPostTypeId(postTypeId);        
        setPostMessage(postMessage);
        setTitle(title);
        setScore(score);
        setCreateDate(creationDate);
        setViewCount(viewCount);
        setAnswerCount(answerCount);
    }

    private void setPostMessage(String postMessage) {  _postMessage = postMessage;  }

    private void setPostTypeId(int postTypeId) { _postTypeId = postTypeId;  }

    private void setTitle(String title) { _title = title;  }

    private void setScore(int score) { _score = score;  }
    
    private void setCreateDate(Date creationDate) { _createDate = creationDate;  }
    
    private void setViewCount(int viewCount) { _viewCount = viewCount;  }
    
    private void setAnswerCount(int answerCount) { _answerCount = answerCount;  }

    public String getPostMessage( ) {  return _postMessage;  }

    public int getPostTypeId( ) { return _postTypeId;  }

    public String getTitle() { return _title;  }

    public int getScore() { return _score;  }
    
    public Date getCreateDate() { return _createDate;  }
    
    public int getViewCount() { return _viewCount;  }
    
    public int getAnswerCount() { return _answerCount;  }

    public static SO_Post fromJson(String postInfo)
    {
        Gson g = new Gson();
        return g.fromJson(postInfo, SO_Post.class);
    }
    
    public String toJson()
    {
        Gson g = new Gson();
        return g.toJson(this);
    }
}