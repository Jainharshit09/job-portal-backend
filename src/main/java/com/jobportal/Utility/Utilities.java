package com.jobportal.Utility;

import com.jobportal.Entity.Sequence;
import com.jobportal.Expection.JobPortalExpection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class Utilities {

    private static MongoOperations mongoOperations;

    @Autowired
    public Utilities(MongoOperations mongoOperations) {
        Utilities.mongoOperations = mongoOperations;
    }

        public static Long getNextSequence(String key) throws JobPortalExpection {
        if (mongoOperations == null) {
            throw new JobPortalExpection("MongoOperations is not initialized!");
        }

        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);

        Sequence seq = mongoOperations.findAndModify(query, update, options, Sequence.class);
        if (seq == null) {
            throw new JobPortalExpection("Unable to get sequence id for key: " + key);
        }
        return seq.getSeq();
    }

    public static String genrateOtp(){
        StringBuilder otp=new StringBuilder();
        SecureRandom secureRandom=new SecureRandom();
        for(int i=0;i<6;i++){
            otp.append(secureRandom.nextInt(10));
        }
        return otp.toString();

    }
}
