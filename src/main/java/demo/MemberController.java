package demo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.Member;
import demo.MemberRepository;

@RestController
public class MemberController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@PostMapping("/addMember")
	public ResponseEntity<Member> savaMember(@RequestBody Member member) {
		memberRepository.save(member);
		return new ResponseEntity<Member>(member, HttpStatus.CREATED);
	}
	
	@GetMapping("/findAllMembers")
	public ResponseEntity<List<Member>> getMembers() {
		List<Member> memberList = memberRepository.findAll();
		return new ResponseEntity<List<Member>>(memberList, HttpStatus.OK);
	}
	
	@GetMapping("/findMember/{id}")
	public ResponseEntity<Member> getMember(@PathVariable int id) {
		Optional<Member> member = memberRepository.findById(id);
		return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteMember/{id}")
	public ResponseEntity<HttpStatus> deleteMember(@PathVariable int id) {
		memberRepository.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/updateMember/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody Member member) {
		member.setId(id);
	    return new ResponseEntity<Member>(memberRepository.save(member), HttpStatus.OK);
	}
	
	@GetMapping("/findMemberByPassword/{password}")
	public ResponseEntity<List<Member>> getMember1(@PathVariable String password) {
		List<Member> member = memberRepository.findByPassword(password);
		return new ResponseEntity<List<Member>>(member, HttpStatus.OK);
	}
	
	@GetMapping("/findMemberByAddress/{address}")
	public ResponseEntity<List<Member>> getMember2(@PathVariable String address) {
		List<Member> member = memberRepository.findByAddress(address);
		return new ResponseEntity<List<Member>>(member, HttpStatus.OK);
	}
	
	@GetMapping("/findAllMembersBySort")
	public ResponseEntity<List<Member>> getMembers1() {
		List<Member> memberList = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		return new ResponseEntity<List<Member>>(memberList, HttpStatus.OK);
	}
}
