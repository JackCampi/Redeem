package es.nacho.redeem.service;

import java.util.Collection;

public interface AreaService {

    Collection<String> capitalizeAreaNames(Collection<String> areaNames);

    String capitalizeAreaName(String areaName);

    Collection<String> lowercaseAreaNames(Collection<String> areaNames);

    String[] lowercaseAreaNames(String[] areaNames);

    String lowercaseAreaName(String areaName);

}
