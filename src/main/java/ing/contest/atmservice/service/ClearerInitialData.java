package ing.contest.atmservice.service;

import ing.contest.atmservice.model.ServiceTask;

import java.util.*;

public class ClearerInitialData {
    public static void clear(List<ServiceTask> serviceTasks) {
        Set<Integer> atmIds = new HashSet<>();
        Map<Integer, ServiceTask> servicesByIds = new HashMap<>();
        List<ServiceTask> servicesToRemove = new ArrayList<>();
        serviceTasks.forEach(serviceTask -> {
                    if (atmIds.add(serviceTask.getAtmId())) {
                        servicesByIds.put(serviceTask.getAtmId(), serviceTask);
                    }
                    else {
                        ServiceTask task = servicesByIds.get(serviceTask.getAtmId());
                        if (serviceTask.getRequestType().isHighPriority(task.getRequestType())) {
                            servicesToRemove.add(task);
                            servicesByIds.put(serviceTask.getAtmId(), serviceTask);
                        } else {
                            servicesToRemove.add(serviceTask);
                        }
                    }
                }
        );
        serviceTasks.removeAll(servicesToRemove);
    }
}
