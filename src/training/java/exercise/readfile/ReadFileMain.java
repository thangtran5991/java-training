/*
 * ReadFileMain
 *
 * Version 1.0
 *
 * 12/14/2020
 *
 * Copyright thang-tran
 */
package training.java.exercise.readfile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ReadFileMain {
    public static void main(String[] args) throws IOException {
        // Create array of files
        FileInfo[] fileInfos = new FileInfo[10];
        fileInfos[0] = new FileInfo("/var/admage/log/file1.log", false);
        fileInfos[1] = new FileInfo("/var/admage/log/file2.log", false);
        fileInfos[2] = new FileInfo("/var/admage/log/file3.log", false);
        fileInfos[3] = new FileInfo("/var/admage/log/file4.log", false);
        fileInfos[4] = new FileInfo("/var/admage/log/file5.log", false);
        fileInfos[5] = new FileInfo("/var/admage/log/file6.log", false);
        fileInfos[6] = new FileInfo("/var/admage/log/file7.log", false);
        fileInfos[7] = new FileInfo("/var/admage/log/file8.log", false);
        fileInfos[8] = new FileInfo("/var/admage/log/file9.log", false);
        fileInfos[9] = new FileInfo("/var/admage/log/file10.log", false);

        // Get callable return
        List<Future<List<String>>> list = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<List<String>> callable;
        for (int i = 0; i < fileInfos.length; i++) {
            callable = new ReadFileThread(fileInfos[i]);
            Future<List<String>> future = executorService.submit(callable);
            list.add(future);
        }

        // Add all to lineList
        List<String> lineList = new ArrayList<>();
        for (Future<List<String>> f : list) {
            try {
                lineList.addAll(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        executorService.shutdown();

        // Split string line and add to LogInfo object
        List<LogInfo> logInfoList = new ArrayList<>();
        for (String item : lineList) {
            String[] stringLine = item.split("\t");
            int adId = Integer.parseInt(stringLine[0]);
            int siteId = Integer.parseInt(stringLine[1]);
            int logType = Integer.parseInt(stringLine[2]);
            int cost = Integer.parseInt(stringLine[3]);
            int wholesale = Integer.parseInt(stringLine[4]);
            LogInfo logInfo = new LogInfo(adId, siteId, logType, cost, wholesale);
            System.out.println("ad_id: " +logInfo.getAdId() + ", " + "site_id: " +logInfo.getSiteId() + ", " + "cost: " +logInfo.getCost() + ", " + "wholesale: " +logInfo.getWholesale());
            logInfoList.add(logInfo);
        }
        System.out.println();
        // Statistic cost with adId
        Map<Integer, Integer> costAdId = statisticCostAdid(logInfoList);

        // Statistic wholesale with adId
        Map<Integer, Integer> wholesaleAdId =  statisticWholesaleAdid(logInfoList);

        // Statistic cost with siteId
        Map<Integer, Integer> costSiteId =  statisticCostSiteid(logInfoList);

        // Statistic wholesale with siteId
        Map<Integer, Integer> wholesaleSiteId =  statisticWholesaleSiteid(logInfoList);

        //Statistic cost with adId and siteId
        Map<MapKey, Integer> costAdIdSiteId = statisticCostAdidSiteid(logInfoList);

        //Stistic wholesale with adId and siteId
        Map<MapKey, Integer> wholesaleAdIdSiteId = statisticWholesaleAdidSiteid(logInfoList);

        //Get adName from file
        List<String> adNameList = getAdName("/var/admage/log/fileadname.log");
        Map<Integer, String> adInfoMap = new HashMap<>();
        for (String item : adNameList) {
            String[] stringLine = item.split("\t");
            int id = Integer.parseInt(stringLine[0]);
            String name = stringLine[1];
            adInfoMap.put(id, name);
        }

        //Get siteName from file
        List<String> siteNameList = getSiteName("/var/admage/log/filesitename.log");
        Map<Integer, String> siteInfoMap = new HashMap<>();
        for (String item : siteNameList) {
            String[] stringLine = item.split("\t");
            int id = Integer.parseInt(stringLine[0]);
            String name = stringLine[1];
            siteInfoMap.put(id, name);
        }

        //Print
        System.out.println("AdId" + "    Name" + "     Cost" + "    Wholesale");
        for (Map.Entry<Integer, String> entry : adInfoMap.entrySet()) {
            System.out.print(entry.getKey() + "       ");
            System.out.print(entry.getValue() + "    ");
            System.out.print(costAdId.get(entry.getKey()) + "$   ");
            System.out.print(wholesaleAdId.get(entry.getKey()) + "$");
            System.out.println();
        }
        System.out.println();

        //Print
        System.out.println("SiteId" + "    Name" + "     Cost" + "    Wholesale");
        for (Map.Entry<Integer, String> entry : siteInfoMap.entrySet()) {
            System.out.print(entry.getKey() + "       ");
            System.out.print(entry.getValue() + "    ");
            System.out.print(costSiteId.get(entry.getKey()) + "$   ");
            System.out.print(wholesaleSiteId.get(entry.getKey()) + "$");
            System.out.println();
        }
        System.out.println();

        // Prinf
        System.out.println("Ad_Id" + "    AdName" + "    SiteId" + "    SiteName" + "      Cost" + "    Wholesale");
        for (Map.Entry<MapKey, Integer> entry : costAdIdSiteId.entrySet()) {
            System.out.print(entry.getKey().getAdId() + "        ");
            System.out.print(adInfoMap.get(entry.getKey().getAdId()) + "        ");
            System.out.print(entry.getKey().getSiteId() + "        ");
            System.out.print(siteInfoMap.get(entry.getKey().getSiteId()) + "      ");
            System.out.print(costAdIdSiteId.get(entry.getKey()) + "$     ");
            System.out.print(wholesaleAdIdSiteId.get(entry.getKey()) + "$");
            System.out.println();
        }
    }

    public static Map statisticCostAdid(List<LogInfo> logInfoList) {
        Map<Integer, Integer> costAdId =  new HashMap<>();
        for (LogInfo item : logInfoList) {
            Boolean check = costAdId.containsKey(item.getAdId());

            // Check exist adId in map
            if (check) {
                int cost = costAdId.get(item.getAdId()) + item.getCost();
                costAdId.replace(item.getAdId(), cost);
            } else {
                costAdId.put(item.getAdId(), item.getCost());
            }
        }

        return costAdId;
    }

    public static Map statisticWholesaleAdid(List<LogInfo> logInfoList) {
        Map<Integer, Integer> wholesaleAdId =  new HashMap<>();
        for (LogInfo item : logInfoList) {
            Boolean check = wholesaleAdId.containsKey(item.getAdId());

            // Check exist adId in map
            if (check) {
                int wholesale = wholesaleAdId.get(item.getAdId()) + item.getWholesale();
                wholesaleAdId.replace(item.getAdId(), wholesale);
            } else {
                wholesaleAdId.put(item.getAdId(), item.getWholesale());
            }
        }

        return wholesaleAdId;
    }

    public static Map statisticCostSiteid(List<LogInfo> logInfoList) {
        Map<Integer, Integer> costSiteId =  new HashMap<>();
        for (LogInfo item : logInfoList) {
            Boolean check = costSiteId.containsKey(item.getSiteId());

            // Check exist siteId in map
            if (check) {
                int cost = costSiteId.get(item.getSiteId()) + item.getCost();
                costSiteId.replace(item.getSiteId(), cost);
            } else {
                costSiteId.put(item.getSiteId(), item.getCost());
            }
        }

        return costSiteId;
    }

    public static Map statisticWholesaleSiteid(List<LogInfo> logInfoList) {
        Map<Integer, Integer> wholesaleSiteId =  new HashMap<>();
        for (LogInfo item : logInfoList) {
            Boolean check = wholesaleSiteId.containsKey(item.getSiteId());

            // Check exist siteId in map
            if (check) {
                int wholasale = wholesaleSiteId.get(item.getSiteId()) + item.getWholesale();
                wholesaleSiteId.replace(item.getSiteId(), wholasale);
            } else {
                wholesaleSiteId.put(item.getSiteId(), item.getWholesale());
            }
        }

        return wholesaleSiteId;
    }

    public static Map statisticCostAdidSiteid(List<LogInfo> logInfoList) {
        Map<MapKey, Integer> costAdIdSiteId = new HashMap<>();
        for (LogInfo item : logInfoList) {
            MapKey mapKey = new MapKey(item.getAdId(), item.getSiteId());
            Boolean check = costAdIdSiteId.containsKey(mapKey);

            // Check exist mapKey in map
            if (check) {
                int cost = costAdIdSiteId.get(mapKey) + item.getCost();
                costAdIdSiteId.replace(mapKey, cost);
            } else {
                costAdIdSiteId.put(mapKey, item.getCost());
            }
        }

        return costAdIdSiteId;
    }

    public static Map statisticWholesaleAdidSiteid(List<LogInfo> logInfoList) {
        Map<MapKey, Integer> wholesaleAdIdSiteId = new HashMap<>();
        for (LogInfo item : logInfoList) {
            MapKey mapKey = new MapKey(item.getAdId(), item.getSiteId());
            Boolean check = wholesaleAdIdSiteId.containsKey(mapKey);

            // Check exist mapKey in map
            if (check) {
                int wholesale = wholesaleAdIdSiteId.get(mapKey) + item.getWholesale();
                wholesaleAdIdSiteId.replace(mapKey, wholesale);
            } else {
                wholesaleAdIdSiteId.put(mapKey, item.getWholesale());
            }
        }

        return wholesaleAdIdSiteId;
    }

    public static List getAdName(String fileName) throws IOException {
        List<String> lineList = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null)
            lineList.add(line);

        return lineList;
    }

    public static List getSiteName(String fileName) throws IOException {
        List<String> lineList = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null)
            lineList.add(line);

        return lineList;
    }
}
