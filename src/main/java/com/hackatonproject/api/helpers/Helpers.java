package com.hackatonproject.api.helpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Helpers {

    private Helpers() {
    }

    public static boolean checkForDuplicates(List list){
        final Set set = new HashSet();

        for (var listItem : list) {
            if(!set.add(listItem)){
                return true;
            }
        }

        return false;
    }
}
