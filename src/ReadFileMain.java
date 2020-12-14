import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ReadFileMain {
    public static void main(String[] args) {
        FileList[] fileLists = new FileList[9];
        fileLists[0] = new FileList("/var/admage/log/file2.log", false);
        fileLists[1] = new FileList("/var/admage/log/file3.log", false);
        fileLists[2] = new FileList("/var/admage/log/file4.log", false);
        fileLists[3] = new FileList("/var/admage/log/file5.log", false);
        fileLists[4] = new FileList("/var/admage/log/file6.log", false);
        fileLists[5] = new FileList("/var/admage/log/file7.log", false);
        fileLists[6] = new FileList("/var/admage/log/file8.log", false);
        fileLists[7] = new FileList("/var/admage/log/file9.log", false);
        fileLists[8] = new FileList("/var/admage/log/file10.log", false);

        List<Future<List<String>>> list = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<List<String>> callable;
        for (int i = 0; i < fileLists.length; i++) {
            callable = new ReadFile(fileLists[i]);
            Future<List<String>> future = executorService.submit(callable);
            list.add(future);
        }
        List<String> list1 = new ArrayList<>();
        for (Future<List<String>> f : list) {
            try {
                list1.addAll(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        executorService.shutdown();
        int obj = list1.size();
        LogInfo[] logInfos = new LogInfo[obj];
        for (int i = 0; i < obj; i ++) {
            String[] strings = list1.get(i).split("\t");
            int ad_id = Integer.parseInt(strings[0]);
            int site_id = Integer.parseInt(strings[1]);
            int log_type = Integer.parseInt(strings[2]);
            int cost = Integer.parseInt(strings[3]);
            int wholesale = Integer.parseInt(strings[4]);
            for (int j = 0; j < obj; j ++) {
                if (j == i) {
                    logInfos[j] = new LogInfo(ad_id, site_id, log_type, cost, wholesale);
                }
            }
        }
        Map<Integer, Integer> costAdId =  new HashMap<>();
        for (int i = 0; i < logInfos.length; i++) {
            Boolean check = costAdId.containsKey(logInfos[i].getAd_id());
            if (check) {
                for (Map.Entry<Integer, Integer> entry : costAdId.entrySet()) {
                    if (logInfos[i].getAd_id() == entry.getKey()) {
                        costAdId.replace(logInfos[i].getAd_id(), entry.getValue() + logInfos[i].getCost());
                    }
                }
            } else {
                costAdId.put(logInfos[i].getAd_id(), logInfos[i].getCost());
            }
        }
        System.out.println("Ad_Id    " + "Cost");
        for (Map.Entry<Integer, Integer> entry : costAdId.entrySet()) {
            System.out.println(entry.getKey() + "        " + entry.getValue());
        }

        Map<Integer, Integer> wholesaleAdId =  new HashMap<>();
        for (int i = 0; i < logInfos.length; i++) {
            Boolean check = wholesaleAdId.containsKey(logInfos[i].getAd_id());
            if (check) {
                for (Map.Entry<Integer, Integer> entry : wholesaleAdId.entrySet()) {
                    if (logInfos[i].getAd_id() == entry.getKey()) {
                        wholesaleAdId.replace(logInfos[i].getAd_id(), entry.getValue() + logInfos[i].getWholesale());
                    }
                }
            } else {
                wholesaleAdId.put(logInfos[i].getAd_id(), logInfos[i].getWholesale());
            }
        }
        System.out.println("Ad_Id    " + "Wholesale");
        for (Map.Entry<Integer, Integer> entry : wholesaleAdId.entrySet()) {
            System.out.println(entry.getKey() + "        " + entry.getValue());
        }

        Map<Integer, Integer> costSiteId =  new HashMap<>();
        for (int i = 0; i < logInfos.length; i++) {
            Boolean check = costSiteId.containsKey(logInfos[i].getSite_id());
            if (check) {
                for (Map.Entry<Integer, Integer> entry : costSiteId.entrySet()) {
                    if (logInfos[i].getSite_id() == entry.getKey()) {
                        costSiteId.replace(logInfos[i].getSite_id(), entry.getValue() + logInfos[i].getCost());
                    }
                }
            } else {
                costSiteId.put(logInfos[i].getSite_id(), logInfos[i].getCost());
            }
        }
        System.out.println("Site_Id    " + "Cost");
        for (Map.Entry<Integer, Integer> entry : costSiteId.entrySet()) {
            System.out.println(entry.getKey() + "          " + entry.getValue());
        }

        Map<Integer, Integer> wholesaleSiteId =  new HashMap<>();
        for (int i = 0; i < logInfos.length; i++) {
            Boolean check = wholesaleSiteId.containsKey(logInfos[i].getSite_id());
            if (check) {
                for (Map.Entry<Integer, Integer> entry : wholesaleSiteId.entrySet()) {
                    if (logInfos[i].getSite_id() == entry.getKey()) {
                        wholesaleSiteId.replace(logInfos[i].getSite_id(), entry.getValue() + logInfos[i].getWholesale());
                    }
                }
            } else {
                wholesaleSiteId.put(logInfos[i].getSite_id(), logInfos[i].getWholesale());
            }
        }
        System.out.println("Site_Id    " + "Wholesale");
        for (Map.Entry<Integer, Integer> entry : wholesaleSiteId.entrySet()) {
            System.out.println(entry.getKey() + "          " + entry.getValue());
        }
    }
}
