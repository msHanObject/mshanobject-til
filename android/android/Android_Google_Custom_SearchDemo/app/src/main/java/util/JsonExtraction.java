package util;

import android.util.Log;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nulgrace on 2017. 11. 16..
 */

public class JsonExtraction {
    //

    public JsonExtraction() {
        //
    }

    public String getTag(String line, List<String> tagList) {
        //
        String result = "";
        Iterator<String> tagIter = tagList.iterator();

        while (tagIter.hasNext()) {
           if (line.contains(tagIter.next())){
               result += line + "\n\n";
           }
        }
        Log.d("tagResult", result);
        return result;
    }
}
