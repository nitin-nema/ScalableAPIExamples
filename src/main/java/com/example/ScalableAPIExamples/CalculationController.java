package com.example.ScalableAPIExamples;

@RestController
@RequestMapping("/api")
public class CalculationController {

    @GetMapping("/add")  // @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Integer> addNumbers(@RequestParam int a, @RequestParam int b) {
            return ResponseEntity.ok(a + b);  //HTTP 200

            // 201 status code
        int result = a+b;
        return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
}


//localhost:8080/api/add
//201 resource created  -