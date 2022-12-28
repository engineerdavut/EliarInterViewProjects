package com.washing.washingmonitoringsystemrestapi.Service.abstracts;

import java.util.List;

import com.washing.washingmonitoringsystemrestapi.Core.utilities.DataResult;
import com.washing.washingmonitoringsystemrestapi.Core.utilities.Result;
import com.washing.washingmonitoringsystemrestapi.Entity.Machine;



public interface MachineService {
	Result create(Machine machine);
	Result update(int id,Machine machine);
	Result delete(int id);
	Result randomCreateMachine(int n);
	DataResult<List<Machine>> getAll();	
	DataResult<Machine> getById(int id);
}
