package demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import demo.Member;

public interface MemberRepository extends MongoRepository<Member, Integer>{

	List<Member> findByPassword(String password);
	
	List<Member> findByAddress(String address);
}
