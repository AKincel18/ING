package ing.contest.atmservice.service;

import ing.contest.atmservice.model.Atm;
import ing.contest.atmservice.model.ServiceTask;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AtmService {
    public List<Atm> calculateOrder(List<ServiceTask> serviceTasks) {
//        ClearerInitialData.clear(serviceTasks);

        Map<Integer, List<ServiceTask>> routesByRegion = new TreeMap<>();
        //map initializer
        serviceTasks.forEach(serviceTask -> {
            if (routesByRegion.containsKey(serviceTask.getRegion())) {
                List<ServiceTask> tasksInRegion = routesByRegion.get(serviceTask.getRegion());
                tasksInRegion.add(serviceTask);
            } else {
                routesByRegion.put(serviceTask.getRegion(), new ArrayList<>(Collections.singletonList(serviceTask)));
            }
        });

        routesByRegion.forEach((key, value) -> ClearerInitialData.clear(value));

        //map to result
        List<Atm> result = routesByRegion.values().stream().flatMap(List::stream).map(Atm::new).toList();
        return new ArrayList<>(result);
    }
}
