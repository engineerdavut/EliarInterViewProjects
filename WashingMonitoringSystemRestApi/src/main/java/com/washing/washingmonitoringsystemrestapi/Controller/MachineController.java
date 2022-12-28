package com.washing.washingmonitoringsystemrestapi.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.washing.washingmonitoringsystemrestapi.Entity.Machine;
import com.washing.washingmonitoringsystemrestapi.Service.concretes.MachineServiceImpl;

@RestController
@RequestMapping("/api/machines/")
public class MachineController {
	private MachineServiceImpl machineServiceImpl;
	@Autowired
	public MachineController(MachineServiceImpl machineServiceImpl) {
		super();
		this.machineServiceImpl = machineServiceImpl;
	}
	@PostMapping("add")
	public ResponseEntity<?> create(@Valid @RequestBody Machine machine){
		return ResponseEntity.ok(this.machineServiceImpl.create(machine));
		
	}
	@PutMapping("update/{id}")
	public ResponseEntity<?> create(@PathVariable int id,@Valid @RequestBody Machine machine){
		return ResponseEntity.ok(this.machineServiceImpl.update(id,machine));
		
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		return ResponseEntity.ok(this.machineServiceImpl.delete(id));	
	}
	@GetMapping("randomCreateMachine")
	public ResponseEntity<?> randomCreateMachine(@RequestParam int n){
		return ResponseEntity.ok(this.machineServiceImpl.randomCreateMachine(n));		
	}
	@GetMapping("getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(this.machineServiceImpl.getAll());		
	}
	@GetMapping("getById") 
	public ResponseEntity<?> getById(@RequestParam int id){
		return ResponseEntity.ok(this.machineServiceImpl.getById(id));

	}
}
