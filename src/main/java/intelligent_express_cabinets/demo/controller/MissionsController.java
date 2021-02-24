package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.Missions;
import intelligent_express_cabinets.demo.service.IMissionsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/missions/basic")
public class MissionsController {

    @Resource
    private IMissionsService missionsService;

    @ApiOperation(value = "获取所有任务信息(任务状态为未接收)")
    @GetMapping("/notReceive")
    public returnBean getMissionsByNotReceive(){
        List<Missions> messagesList = missionsService.getMissionsByNotReceive();
        return returnBean.success("获取所有任务信息成功!",messagesList);
    }

    @ApiOperation(value = "获取所有任务信息(任务状态为已完成)")
    @GetMapping("/finishReceives")
    public returnBean getMissionsByFinishReceive(){
        List<Missions> messages = missionsService.getMissionsByFinishReceive();
        return returnBean.success("获取所有任务信息成功!",messages);
    }

    @ApiOperation(value = "获取所有任务信息(任务状态为已接收)")
    @GetMapping("/receives")
    public returnBean getMissionsByReceive(){
        List<Missions> messages = missionsService.getMissionsByReceive();
        return returnBean.success("获取所有任务信息成功!",messages);
    }

    @ApiOperation(value = "接收任务")
    @PostMapping("/receive/{missionId}")
    public returnBean receiveMissions(@PathVariable Integer missionId){
        Missions mission = missionsService.getById(missionId);
        if (mission.getMissionStatus()==2){
            return returnBean.error("任务接收失败!");
        }
        //设置任务状态为2:已接收
        mission.setMissionStatus(2);
        missionsService.updateById(mission);
        return returnBean.success("任务接收成功!");
    }

    @ApiOperation(value = "根据id删除任务")
    @DeleteMapping("/delete/{missionId}")
    public returnBean deleteMissions(@PathVariable Integer missionId){
        Missions mission = missionsService.getById(missionId);
        //设置任务状态为4:消息不可见(专柜员删除，管理员可见)
        mission.setMissionStatus(4);
        missionsService.updateById(mission);
        return returnBean.success("删除任务成功!");
    }

    @ApiOperation(value = "取消任务")
    @PostMapping("/cancel/{missionId}")
    public returnBean cancelMissions(@PathVariable Integer missionId){
        Missions mission = missionsService.getById(missionId);
        //设置任务状态为1:未接收
        mission.setMissionStatus(1);
        //修改任务执行人
        mission.setOperatorId(0);
        missionsService.updateById(mission);
        return returnBean.success("取消任务成功!");
    }

    @ApiOperation(value = "完成任务")
    @PostMapping("/complete/{missionId}")
    public returnBean completeMissions(@PathVariable Integer missionId){
        Missions mission = missionsService.getById(missionId);
        //设置任务状态为3:已完成
        mission.setMissionStatus(3);
        mission.setMissionEndData(LocalDateTime.now());
        missionsService.updateById(mission);
        return returnBean.success("完成任务成功!");
    }
}
