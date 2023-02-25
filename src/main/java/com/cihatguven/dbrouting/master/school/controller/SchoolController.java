package com.cihatguven.dbrouting.master.school.controller;

import com.cihatguven.dbrouting.master.school.controller.requests.SaveSchoolRequest;
import com.cihatguven.dbrouting.master.school.controller.requests.UpdateSchoolRequest;
import com.cihatguven.dbrouting.master.school.entity.School;
import com.cihatguven.dbrouting.master.school.manager.SchoolManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school")
public class SchoolController {

    private final SchoolManager schoolManager;

    @PostMapping("/create")
    public ResponseEntity<School> insertSchool(@RequestBody SaveSchoolRequest request) {
        return ResponseEntity.ok(schoolManager.createSchool(request));
    }

    @PutMapping("/{schoolId}/update")
    public ResponseEntity<School> updateSchool(@PathVariable String schoolId, @RequestBody UpdateSchoolRequest request) {
        return ResponseEntity.ok(schoolManager.updateSchool(schoolId, request));
    }

    @GetMapping("/{schoolId}/get")
    public ResponseEntity<School> getSchoolById(@PathVariable String schoolId) {
        return ResponseEntity.ok(schoolManager.getSchoolById(schoolId));
    }

    @GetMapping("/is_exists")
    public ResponseEntity<Boolean> isSchoolExists(@RequestParam String code) {
        return ResponseEntity.ok(schoolManager.schoolExistsByCode(code));
    }

    @DeleteMapping("/{schoolId}/delete")
    public ResponseEntity<Void> deleteSchool(@PathVariable String schoolId) {
        schoolManager.deleteSchool(schoolId);
        return ResponseEntity.ok().build();
    }

}