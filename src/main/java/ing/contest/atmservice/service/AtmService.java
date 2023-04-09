package ing.contest.atmservice.service;

import ing.contest.atmservice.model.Atm;
import ing.contest.atmservice.model.RequestType;
import ing.contest.atmservice.model.ServiceTask;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AtmService {
    public List<Atm> calculateOrder(List<ServiceTask> serviceTasks) {
        filterByHighestPriority(serviceTasks);
        Map<Integer, Map<RequestType, List<ServiceTask>>> routesByRegion = calculateRoutesByRegion(serviceTasks);

        return routesByRegion.entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().entrySet().stream())
                .flatMap(entry -> entry.getValue().stream())
                .map(Atm::new)
                .toList();
    }

    private void filterByHighestPriority(List<ServiceTask> serviceTasks) {
        Map<String, ServiceTask> uniqueServices = new HashMap<>();
        serviceTasks.stream()
                .filter(serviceTask -> isTaskUnique(serviceTask, uniqueServices.get(serviceTask.getRegionAtmId())))
                .forEach(serviceTask -> uniqueServices.put(serviceTask.getRegionAtmId(), serviceTask));
        serviceTasks.retainAll(uniqueServices.values());
    }

    private boolean isTaskUnique(ServiceTask serviceTask, ServiceTask existingTask) {
        return existingTask == null || serviceTask.getRequestType().isHighPriority(existingTask.getRequestType());
    }

    private Map<Integer, Map<RequestType, List<ServiceTask>>> calculateRoutesByRegion(List<ServiceTask> serviceTasks) {
        Map<Integer, Map<RequestType, List<ServiceTask>>> routesByRegion = new TreeMap<>(Comparator.naturalOrder());
        serviceTasks.forEach(serviceTask -> routesByRegion
                .computeIfAbsent(serviceTask.getRegion(), key -> new TreeMap<>(Comparator.comparing(RequestType::ordinal)))
                .computeIfAbsent(serviceTask.getRequestType(), key -> new ArrayList<>())
                .add(serviceTask)
        );
        return routesByRegion;
    }
}
