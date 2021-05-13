package es.nacho.redeem.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.util.StringUtils;

@Service
public class AreaServiceImpl implements AreaService{
    @Override
    public Collection<String> capitalizeAreaNames(Collection<String> areaNames) {
        Collection<String> newAreaNames = new ArrayList<>();
        areaNames.forEach(areaName -> newAreaNames.add(capitalizeAreaName(areaName)));
        return newAreaNames;
    }

    @Override
    public String capitalizeAreaName(String areaName) {
        return areaName.equals("gerencia") ? "Gerencia (Admin)" : capitalizeWord(areaName);
    }

    private String capitalizeWord(String word){

        String[] singleWords = word.split(" ");

        for (int i = 0; i < singleWords.length; i++) {
            singleWords[i] = StringUtils.capitalize(singleWords[i]);
        }

        return String.join(" ", singleWords);
    }

    @Override
    public Collection<String> lowercaseAreaNames(Collection<String> areaNames) {

        Collection<String> newAreaNames = new ArrayList<>();

        areaNames.forEach(areaName -> {

            if(areaName.equals("Gerencia (Admin)")) newAreaNames.add("gerencia");

            else newAreaNames.add(areaName.trim().toLowerCase());

        });

        return newAreaNames;
    }

    @Override
    public String[] lowercaseAreaNames(String[] areaNames) {

        int length = areaNames.length;

        String[] newAreaNames = new String[length];

        for(int i = 0; i< length; i++){
            if(areaNames[i].equals("Gerencia (Admin)")) newAreaNames[i] = "gerencia";
            else newAreaNames[i] = areaNames[i].trim().toLowerCase();
        }

        return newAreaNames;
    }

    @Override
    public String lowercaseAreaName(String areaName) {
        if(areaName.equals("Gerencia (Admin)")) return "gerencia";
        return areaName.trim().toLowerCase();
    }
}
