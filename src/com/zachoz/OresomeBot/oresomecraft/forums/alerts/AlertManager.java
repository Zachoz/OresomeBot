package com.zachoz.OresomeBot.oresomecraft.forums.alerts;

import com.zachoz.OresomeBot.OresomeBot;
import com.zachoz.OresomeBot.oresomecraft.forums.ForumAccount;

import java.util.ArrayList;

public class AlertManager {

    public static ArrayList<Alert> getNewAlerts(ForumAccount account) {
        ArrayList<Alert> latestAlerts = account.queryAlerts();
        ArrayList<Alert> newAlerts = new ArrayList<Alert>();

        for (Alert newAlert : latestAlerts) {
            if (!(account.getAlerts() == null) && !account.getAlerts().isEmpty() &&
                    Integer.parseInt(account.getAlerts().get(0).getDate()) < Integer.parseInt(newAlert.getDate())) {
                newAlerts.add(newAlert);
            }
        }

        return newAlerts;
    }

    public static void checkAndSendNewAlerts(ForumAccount account, String sendTo) {
        if (account.getAlerts() == null) { // Has no previously cached alerts
            account.setAlerts(account.queryAlerts());
            return;
        }

        ArrayList<Alert> alerts = AlertManager.getNewAlerts(account);

        if (!alerts.isEmpty()) {
            for (Alert alert : alerts) {
                OresomeBot.getBot().sendIRC().message(sendTo, alert.getIRCMessage());
            }
        }

        account.setAlerts(account.queryAlerts());
    }

}
