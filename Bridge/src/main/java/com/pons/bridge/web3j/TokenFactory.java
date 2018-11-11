package com.pons.bridge.web3j;

import java.math.BigInteger;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

import com.pons.bridge.contracts.EscrowERC20;
import com.pons.bridge.contracts.MonetaryToken;
import com.pons.bridge.contracts.PonsERC721;

@Service("tokenFactory")
public class TokenFactory {

	private Web3j web3j;
	private Credentials credentials;
	private MasterNode masterNode;
	
	public TokenFactory(){
		masterNode = MasterNode.getInstance();
		web3j = masterNode.getWeb3j();
		credentials = masterNode.getCredentials();
	}
		
	public boolean createERC20(){
		System.out.println("Deploying smart contract ERC20");
        ContractGasProvider contractGasProvider = new DeployGasProvider();
        MonetaryToken erc20contract;
        try{
        	erc20contract = MonetaryToken.deploy(web3j, credentials, contractGasProvider).send();
        }catch(Exception e){
        	e.printStackTrace();
        	return false;
        }
        String contractAddress = erc20contract.getContractAddress();
        System.out.println("ERC20 deployed to: " + contractAddress);
        masterNode.setErc20TokenAddress(erc20contract.getContractAddress());
        return true;
	}
	
	public MonetaryToken loadERC20Token(Credentials credentials){
		String contractAddress = masterNode.getErc20TokenAddress();
		ContractGasProvider contractGasProvider = new DeployGasProvider();
		return MonetaryToken.load(contractAddress, web3j, credentials, contractGasProvider);
	}
    
	public boolean createERC721(){
        System.out.println("Deploying smart contract ERC721");
        ContractGasProvider contractGasProvider = new DeployGasProvider();
        PonsERC721 erc721Contract;
		try {
			erc721Contract = PonsERC721.deploy(web3j, credentials, contractGasProvider).send();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        String contractAddress = erc721Contract.getContractAddress();
        System.out.println("ERC721 deployed to: " + contractAddress);
        masterNode.setErc721ContractAddress(erc721Contract.getContractAddress());
        return true;
	}
	
	public PonsERC721 loadERC721Token(Credentials credentials){
		String contractAddress = masterNode.getErc721ContractAddress();
		ContractGasProvider contractGasProvider = new DeployGasProvider();
		return PonsERC721.load(contractAddress, web3j, credentials, contractGasProvider);
	}
	
	public boolean createEscrowERC20(String from, String to, String passcode, String quantity){
        System.out.println("Deploying smart contract Escrow ERC20");
        ContractGasProvider contractGasProvider = new DeployGasProvider();
        EscrowERC20 escrowERC20;
		try {
			escrowERC20 = EscrowERC20.deploy(web3j, credentials, contractGasProvider).send();
			escrowERC20.setParams(from, to, passcode, new BigInteger(quantity)).send();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        String contractAddress = escrowERC20.getContractAddress();
        System.out.println("EscrowERC20 deployed to: " + contractAddress);
        masterNode.setEscrowERC20ContractAddress(contractAddress);
        return true;
	}
	
	public EscrowERC20 loadEscrowERC20(Credentials credentials){
		String contractAddress = masterNode.getEscrowERC20ContractAddress();
		ContractGasProvider contractGasProvider = new DeployGasProvider();
		return EscrowERC20.load(contractAddress, web3j, credentials, contractGasProvider);
	}
   
}
