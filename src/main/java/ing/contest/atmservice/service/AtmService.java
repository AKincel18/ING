package ing.contest.atmservice.service;

import ing.contest.atmservice.model.Atm;
import ing.contest.atmservice.model.RequestType;
import ing.contest.atmservice.model.ServiceTask;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    private List<ServiceTask> filterByHighestPriority(List<ServiceTask> serviceTasks) {
        return serviceTasks.stream()
                .collect(Collectors.groupingBy(ServiceTask::getRegionAtmId, Collectors.maxBy(Comparator.comparing(ServiceTask::getRequestType))))
                .values().stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    private List<ServiceTask> filterByHighestPriority2(List<ServiceTask> serviceTasks) {
        Map<String, ServiceTask> taskMap = new HashMap<>();
        for (ServiceTask task : serviceTasks) {
            String key = task.getRegion() + "-" + task.getAtmId();
            ServiceTask existingTask = taskMap.get(key);
            if (existingTask == null || task.getRequestType().isHighPriority(existingTask.getRequestType())) {
                taskMap.put(key, task);
            }
        }
        return new ArrayList<>(taskMap.values());
    }

    private List<ServiceTask> removeDuplicatedServices(List<ServiceTask> serviceTasks) {
        Map<String, ServiceTask> uniqueServices = new HashMap<>();
        List<ServiceTask> servicesToRemove = new ArrayList<>();
        serviceTasks.forEach(serviceTask -> {
            String key = serviceTask.toString();
            if (uniqueServices.containsKey(key)) {
                ServiceTask existingTask = uniqueServices.get(key);
                if (shouldReplace(existingTask, serviceTask)) {
                    servicesToRemove.add(existingTask);
                    uniqueServices.put(key, serviceTask);
                } else {
                    servicesToRemove.add(serviceTask);
                }
            } else {
                uniqueServices.put(key, serviceTask);
            }
        });
        serviceTasks.removeAll(servicesToRemove);
        return serviceTasks;
    }

    private boolean shouldReplace(ServiceTask existingTask, ServiceTask newTask) {
        return newTask.getRequestType().isHighPriority(existingTask.getRequestType());
    }

//    private List<ServiceTask> removeDuplicatedServices(List<ServiceTask> serviceTasks) {
//        return new ArrayList<>(serviceTasks.stream()
//                .collect(Collectors.toMap(
//                        ServiceTask::toString,
//                        Function.identity(),
//                        getTaskWithHigherPriority(),
//                        LinkedHashMap::new
//                ))
//                .values());
//    }
//
//    private BinaryOperator<ServiceTask> getTaskWithHigherPriority() {
//        return (existingTask, newTask) ->
//                newTask.getRequestType().isHighPriority(existingTask.getRequestType()) ? newTask : existingTask;
//    }

    private Map<Integer, Map<RequestType, List<ServiceTask>>> calculateRoutesByRegion(List<ServiceTask> serviceTasks) {
        Map<Integer, Map<RequestType, List<ServiceTask>>> routesByRegion = new TreeMap<>(Comparator.naturalOrder());
        serviceTasks.forEach(serviceTask ->
                routesByRegion
                        .computeIfAbsent(serviceTask.getRegion(), key -> new TreeMap<>(Comparator.comparing(RequestType::ordinal)))
                        .computeIfAbsent(serviceTask.getRequestType(), key -> new ArrayList<>())
                        .add(serviceTask)
        );
        return routesByRegion;
    }

}
