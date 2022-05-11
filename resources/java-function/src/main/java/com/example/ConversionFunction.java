package com.example;

import org.apache.pulsar.client.api.schema.GenericObject;
import org.apache.pulsar.client.api.schema.GenericRecord;
import org.apache.pulsar.common.schema.KeyValue;
import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;
import org.slf4j.Logger;
import com.google.gson.Gson;

public class ConversionFunction implements Function<GenericObject, String> {
    @Override
    public String process(GenericObject record, Context context) {
        Logger LOG = context.getLogger();

        KeyValue<GenericRecord, GenericRecord> keyValue = (KeyValue<GenericRecord, GenericRecord>) record.getNativeObject();

        try{
            int postTypeId = (int) keyValue.getValue().getField("posttypeid");
            String title = (String) keyValue.getValue().getField("title");
            int score = (int) keyValue.getValue().getField("score");
            int viewCount = (int) keyValue.getValue().getField("viewcount");
            int answerCount = (int) keyValue.getValue().getField("answercount");
            
            SO_Post post = new SO_Post(postTypeId, title, score, viewCount, answerCount);
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
    private String _title;
    private int _score;
    private int _viewCount;
    private int _answerCount;

    public SO_Post(
        int postTypeId,
        String title,
        int score,
        int viewCount,
        int answerCount
    ){
        setPostTypeId(postTypeId);    
        setTitle(title);
        setScore(score);
        setViewCount(viewCount);
        setAnswerCount(answerCount);
    }

    private void setPostTypeId(int postTypeId) { _postTypeId = postTypeId;  }

    private void setTitle(String title) { _title = title;  }

    private void setScore(int score) { _score = score;  }
    
    private void setViewCount(int viewCount) { _viewCount = viewCount;  }
    
    private void setAnswerCount(int answerCount) { _answerCount = answerCount;  }

    public int getPostTypeId( ) { return _postTypeId;  }

    public String getTitle() { return _title;  }

    public int getScore() { return _score;  }
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