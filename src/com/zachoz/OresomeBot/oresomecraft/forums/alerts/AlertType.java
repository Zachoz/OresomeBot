package com.zachoz.OresomeBot.oresomecraft.forums.alerts;

public enum AlertType {
    POST, QUOTE, LIKE, TAG, FOLLOWING, PROFILE_POST, PROFILE_LIKE, TROPHY;

    /* JSON responses:
     *
     * 'insert' : POST
     * 'quote' : QUOTE
     * 'like' : LIKE
     * 'tag' : TAG
     * 'following' : FOLLOWING
     * 'profile_post' : PROFILE_POST
     */

    public static AlertType fromString(String type) {
        AlertType alertType = null;
        if (type.equalsIgnoreCase("insert") || type.equalsIgnoreCase("post")) alertType = AlertType.POST;
        if (type.equalsIgnoreCase("quote")) alertType = AlertType.QUOTE;
        if (type.equalsIgnoreCase("like")) alertType = AlertType.LIKE;
        if (type.equalsIgnoreCase("tag")) alertType = AlertType.TAG;
        if (type.equalsIgnoreCase("following")) alertType = AlertType.FOLLOWING;
        if (type.equalsIgnoreCase("profile_post")) alertType = AlertType.PROFILE_POST;
        if (type.equalsIgnoreCase("profile_like")) alertType = AlertType.PROFILE_LIKE;
        if (type.equalsIgnoreCase("trophy")) alertType = AlertType.TROPHY;
        return alertType;
    }

    public static boolean threadBased(AlertType type) {
        return type == AlertType.POST || type == AlertType.QUOTE || type == AlertType.LIKE || type == AlertType.TAG;
    }

}
