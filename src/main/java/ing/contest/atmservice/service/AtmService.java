package ing.contest.atmservice.service;

import ing.contest.atmservice.model.Atm;
import ing.contest.atmservice.model.RequestType;
import ing.contest.atmservice.model.ServiceTask;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AtmService {
    public List<Atm> calculateOrder(List<ServiceTask> serviceTasks) {
        removeDuplicatedServices(serviceTasks);
        Map<Integer, Map<RequestType, List<ServiceTask>>> routesByRegion = calculateRoutesByRegion(serviceTasks);

        return new ArrayList<>(routesByRegion.values()
                .stream()
                .flatMap(map -> map.entrySet().stream())
                .flatMap(map -> map.getValue().stream())
                .map(Atm::new)
                .toList());
    }

    private void removeDuplicatedServices(List<ServiceTask> serviceTasks) {
        Set<String> atmIds = new HashSet<>();
        Map<String, ServiceTask> servicesByIds = new HashMap<>();
        List<ServiceTask> servicesToRemove = new ArrayList<>();
        serviceTasks.forEach(serviceTask -> {
                    if (atmIds.add(serviceTask.toString())) {
                        servicesByIds.put(serviceTask.toString(), serviceTask);
                    }
                    else {
                        ServiceTask task = servicesByIds.get(serviceTask.toString());
                        if (serviceTask.getRequestType().isHighPriority(task.getRequestType())) {
                            servicesToRemove.add(task);
                            servicesByIds.put(serviceTask.toString(), serviceTask);
                        } else {
                            servicesToRemove.add(serviceTask);
                        }
                    }
                }
        );
        serviceTasks.removeAll(servicesToRemove);
    }

    private Map<Integer, Map<RequestType, List<ServiceTask>>> calculateRoutesByRegion(List<ServiceTask> serviceTasks) {
        Map<Integer, Map<RequestType, List<ServiceTask>>> routesByRegion = new TreeMap<>(Comparator.naturalOrder());
        serviceTasks.forEach(serviceTask -> {
            if (routesByRegion.containsKey(serviceTask.getRegion())) {
                Map<RequestType, List<ServiceTask>> tasksInRegion = routesByRegion.get(serviceTask.getRegion());
                if (tasksInRegion.containsKey(serviceTask.getRequestType())) {
                    tasksInRegion.get(serviceTask.getRequestType()).add(serviceTask);
                } else {
                    tasksInRegion.put(serviceTask.getRequestType(), new ArrayList<>(Collections.singletonList(serviceTask)));
                }
            } else {
                Map<RequestType, List<ServiceTask>> routesByTypeInRegion = new TreeMap<>(Comparator.comparing(RequestType::ordinal));
                routesByTypeInRegion.put(serviceTask.getRequestType(), new ArrayList<>(Collections.singletonList(serviceTask)));
                routesByRegion.put(serviceTask.getRegion(), routesByTypeInRegion);
            }
        });
        return routesByRegion;
    }

}
