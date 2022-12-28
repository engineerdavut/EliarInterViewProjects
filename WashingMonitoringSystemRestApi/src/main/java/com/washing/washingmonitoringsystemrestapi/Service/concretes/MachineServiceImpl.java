package com.washing.washingmonitoringsystemrestapi.Service.concretes;


import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.washing.washingmonitoringsystemrestapi.Core.utilities.DataResult;
import com.washing.washingmonitoringsystemrestapi.Core.utilities.Result;
import com.washing.washingmonitoringsystemrestapi.Core.utilities.SuccessDataResult;
import com.washing.washingmonitoringsystemrestapi.Core.utilities.SuccessResult;
import com.washing.washingmonitoringsystemrestapi.Dao.MachineRepository;
import com.washing.washingmonitoringsystemrestapi.Entity.Machine;
import com.washing.washingmonitoringsystemrestapi.Service.abstracts.MachineService;


@Service
public class MachineServiceImpl  implements MachineService{
	private MachineRepository machineRepository;
	@Autowired
	public MachineServiceImpl(MachineRepository machineRepository) {
		super();
		this.machineRepository = machineRepository;
	}
	
	@Override
	public Result create(Machine machine) {
		// TODO Auto-generated method stub
		this.machineRepository.save(machine);
		return new SuccessResult ();
	}
	@Override
	public Result randomCreateMachine(int n) {
		// TODO Auto-generated method stub
		float[] heatArray = {30.0f, 40.0f,60.0f,75.0f,90.0f};
		float[] drumSpeedArray = {100.0f, 200,300,400,500,600,700,800,900,1000};
		float[] levelArray= {10,20,30,40,50,60,70.0f};//water level mi?
		int[] machineid=new int[n];
		Random rand = new Random();
		
		for(int i=0;i<n;i++) {
			Machine myMachine=new Machine();
			myMachine.setDrumSpeed(drumSpeedArray[rand.nextInt(drumSpeedArray.length)]);
			myMachine.setHeat(heatArray[rand.nextInt(heatArray.length)]);
			myMachine.setLevel(levelArray[rand.nextInt(levelArray.length)]);
			myMachine.setIsopenthedoor(ThreadLocalRandom.current().nextBoolean());
			myMachine=this.machineRepository.save(myMachine);
			machineid[i]=myMachine.getId();
		}
		Timer myTimer=new Timer();
		TimerTask  myTask=new TimerTask() {
			@Override
			public void run() {
				for(int i=0;i<n;i++) {
					Machine machineUpdate=machineRepository.findByIdOrError(machineid[i]);
					machineUpdate.setDrumSpeed(drumSpeedArray[rand.nextInt(drumSpeedArray.length)]);
					machineUpdate.setHeat(heatArray[rand.nextInt(heatArray.length)]);
					machineUpdate.setLevel(levelArray[rand.nextInt(levelArray.length)]);
					machineUpdate.setIsopenthedoor(ThreadLocalRandom.current().nextBoolean());
					machineRepository.save(machineUpdate);
				}

			}
			
		};
		myTimer.schedule(myTask,0,1000);
		return new SuccessResult ();
	}

	@Override
	public Result update(int id, Machine machine) {
		// TODO Auto-generated method stub
		Machine myMachine=this.machineRepository.findByIdOrError(id);
		myMachine.setDrumSpeed(machine.getDrumSpeed());
		myMachine.setHeat(machine.getHeat());
		myMachine.setLevel(machine.getLevel());
		myMachine.setIsopenthedoor(machine.isopenthedoor());
		this.machineRepository.save(machine);
		return new SuccessResult ();
	}

	@Override
	public Result delete(int id) {
		// TODO Auto-generated method stub
		Machine myMachine=this.machineRepository.findByIdOrError(id);
		this.machineRepository.delete(myMachine);
		return new SuccessResult ();
	}

	@Override
	public DataResult<List<Machine>> getAll() {
		return new SuccessDataResult<List<Machine>>
		(this.machineRepository.findAll(),"data listelendi.");
	}

	@Override
	public DataResult<Machine> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
