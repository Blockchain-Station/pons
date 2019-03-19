package com.pons.bridge.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.0.1.
 */
public class MonetaryToken extends Contract {
    private static final String BINARY = "60c0604052600460808190527f48312e300000000000000000000000000000000000000000000000000000000060a090815262000040916009919062000169565b503480156200004e57600080fd5b50620f4240600481905533600090815260208181526040808320939093556005919091558151808301909252600e8083527f4d6f6e657461727920546f6b656e00000000000000000000000000000000000092909101918252620000b59160069162000169565b506007805460ff191660121790556040805180820190915260038082527f4d4f4e00000000000000000000000000000000000000000000000000000000006020909201918252620001099160089162000169565b50600a8054600160a060020a03191633908117909155604080516020818101808452600080845294855260029091529190922091516200014b92919062000169565b50336000908152600160205260409020805460ff191690556200020e565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001ac57805160ff1916838001178555620001dc565b82800160010185558215620001dc579182015b82811115620001dc578251825591602001919060010190620001bf565b50620001ea929150620001ee565b5090565b6200020b91905b80821115620001ea5760008155600101620001f5565b90565b61108a806200021e6000396000f3006080604052600436106100ed5763ffffffff60e060020a60003504166306fdde0381146100ff578063095ea7b31461018957806318160ddd146101c157806323b872dd146101e8578063290bb45314610212578063313ce5671461026d578063521968121461029857806354fd4d50146102ad578063599e74af146102c257806370a082311461031b5780639358928b1461033c57806395d89b4114610351578063a9059cbb14610366578063bbf770d51461038a578063bed34bba146103ee578063cae9ca5114610485578063ce0e19ba146104ee578063dd62ed3e14610547578063dd83fdf61461056e575b3480156100f957600080fd5b50600080fd5b34801561010b57600080fd5b506101146105c7565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561014e578181015183820152602001610136565b50505050905090810190601f16801561017b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561019557600080fd5b506101ad600160a060020a0360043516602435610655565b604080519115158252519081900360200190f35b3480156101cd57600080fd5b506101d66106bc565b60408051918252519081900360200190f35b3480156101f457600080fd5b506101ad600160a060020a03600435811690602435166044356106c2565b34801561021e57600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261026b9436949293602493928401919081908401838280828437509497506107ad9650505050505050565b005b34801561027957600080fd5b506102826107d1565b6040805160ff9092168252519081900360200190f35b3480156102a457600080fd5b506101d66107da565b3480156102b957600080fd5b506101146107f9565b3480156102ce57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101ad9436949293602493928401919081908401838280828437509497506108549650505050505050565b34801561032757600080fd5b506101d6600160a060020a03600435166109aa565b34801561034857600080fd5b506101d66109c5565b34801561035d57600080fd5b506101146109cb565b34801561037257600080fd5b506101ad600160a060020a0360043516602435610a26565b34801561039657600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101ad94369492936024939284019190819084018382808284375094975050509235600160a060020a03169350610abd92505050565b3480156103fa57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101ad94369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610b639650505050505050565b34801561049157600080fd5b50604080516020600460443581810135601f81018490048402850184019095528484526101ad948235600160a060020a0316946024803595369594606494920191908190840183828082843750949750610cf79650505050505050565b3480156104fa57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101d6943694929360249392840191908190840183828082843750949750610e929650505050505050565b34801561055357600080fd5b506101d6600160a060020a0360043581169060243516610ed9565b34801561057a57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101ad943694929360249392840191908190840183828082843750949750610f049650505050505050565b6006805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561064d5780601f106106225761010080835404028352916020019161064d565b820191906000526020600020905b81548152906001019060200180831161063057829003601f168201915b505050505081565b336000818152600360209081526040808320600160a060020a038716808552908352818420869055815186815291519394909390927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925928290030190a35060015b92915050565b60045481565b600160a060020a038316600090815260208190526040812054821180159061070d5750600160a060020a03841660009081526003602090815260408083203384529091529020548211155b80156107195750600082115b156107a257600160a060020a0380841660008181526020818152604080832080548801905593881680835284832080548890039055600382528483203384528252918490208054879003905583518681529351929391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a35060016107a6565b5060005b9392505050565b33600090815260026020908152604090912082516107cd92840190610fc6565b5050565b60075460ff1681565b600a54600160a060020a03166000908152600b60205260409020545b90565b6009805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561064d5780601f106106225761010080835404028352916020019161064d565b336000908152600260208181526040808420805482516001821615610100026000190190911694909404601f81018490048402850184019092528184526108f593929091908301828280156108ea5780601f106108bf576101008083540402835291602001916108ea565b820191906000526020600020905b8154815290600101906020018083116108cd57829003601f168201915b505050505083610b63565b1561091c5750336000908152600160208190526040909120805460ff1916821790556109a5565b33600090815260026020818152604092839020805484516001821615610100026000190190911693909304601f81018390048302840183019094528383526109869390918301828280156108ea5780601f106108bf576101008083540402835291602001916108ea565b15156109a55750336000908152600160205260408120805460ff191690555b919050565b600160a060020a031660009081526020819052604090205490565b60055481565b6008805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561064d5780601f106106225761010080835404028352916020019161064d565b336000908152602081905260408120548211801590610a455750600082115b15610ab5573360008181526020818152604080832080548790039055600160a060020a03871680845292819020805487019055805186815290519293927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a35060016106b6565b5060006106b6565b600160a060020a0381166000908152600260208181526040808420805482516001821615610100026000190190911694909404601f81018490048402850184019092528184526107a69392909190830182828015610b5c5780601f10610b3157610100808354040283529160200191610b5c565b820191906000526020600020905b815481529060010190602001808311610b3f57829003601f168201915b5050505050845b6000826040518082805190602001908083835b60208310610b955780518252601f199092019160209182019101610b76565b5181516020939093036101000a60001901801990911692169190911790526040519201829003909120159250829150610c2a90505750816040518082805190602001908083835b60208310610bfb5780518252601f199092019160209182019101610bdc565b5181516020939093036101000a6000190180199091169216919091179052604051920182900390912015925050505b15610c37575060006106b6565b816040518082805190602001908083835b60208310610c675780518252601f199092019160209182019101610c48565b51815160209384036101000a6000190180199092169116179052604051919093018190038120885190955088945090928392508401908083835b60208310610cc05780518252601f199092019160209182019101610ca1565b5181516020939093036101000a60001901801990911692169190911790526040519201829003909120939093149695505050505050565b336000818152600360209081526040808320600160a060020a038816808552908352818420879055815187815291519394909390927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925928290030190a383600160a060020a031660405180807f72656365697665417070726f76616c28616464726573732c75696e743235362c81526020017f616464726573732c627974657329000000000000000000000000000000000000815250602e019050604051809103902060e060020a9004338530866040518563ffffffff1660e060020a0281526004018085600160a060020a0316600160a060020a0316815260200184815260200183600160a060020a0316600160a060020a03168152602001828051906020019080838360005b83811015610e37578181015183820152602001610e1f565b50505050905090810190601f168015610e645780820380516001836020036101000a031916815260200191505b509450505050506000604051808303816000875af1925050501515610e8857600080fd5b5060019392505050565b600a54600160a060020a03166000908152600b60209081526040822080546001810180835591845282842085519293610ed2939190920191860190610fc6565b5092915050565b600160a060020a03918216600090815260036020908152604080832093909416825291909152205490565b6000805b610f106107da565b811015610fbb57600a54600160a060020a03166000908152600b602052604090208054610fa5919083908110610f4257fe5b600091825260209182902001805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610b5c5780601f10610b3157610100808354040283529160200191610b5c565b15610fb35760009150610fc0565b600101610f08565b600191505b50919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061100757805160ff1916838001178555611034565b82800160010185558215611034579182015b82811115611034578251825591602001919060010190611019565b50611040929150611044565b5090565b6107f691905b80821115611040576000815560010161104a5600a165627a7a723058202f2fe04341c2d6f8ff6f8f19f09df5288961a6e70411a875151832e5cbefcc100029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_SETPASSWORD = "setPassword";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_GETLOANCOUNT = "getLoanCount";

    public static final String FUNC_VERSION = "version";

    public static final String FUNC_LOCKCONTRACT = "lockContract";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_CIRCULATINGSUPPLY = "circulatingSupply";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_CHECKLOCKSTATUS = "checkLockStatus";

    public static final String FUNC_COMPARESTRINGS = "compareStrings";

    public static final String FUNC_APPROVEANDCALL = "approveAndCall";

    public static final String FUNC_APPENDSTRING = "appendString";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_VALIDLOANADDRESS = "validLoanAddress";

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected MonetaryToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MonetaryToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MonetaryToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MonetaryToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_from), 
                new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setPassword(String pass) {
        final Function function = new Function(
                FUNC_SETPASSWORD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(pass)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getLoanCount() {
        final Function function = new Function(FUNC_GETLOANCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> version() {
        final Function function = new Function(FUNC_VERSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> lockContract(String local_pass) {
        final Function function = new Function(
                FUNC_LOCKCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(local_pass)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balanceOf(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> circulatingSupply() {
        final Function function = new Function(FUNC_CIRCULATINGSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> checkLockStatus(String local_pass, String checkAddress) {
        final Function function = new Function(FUNC_CHECKLOCKSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(local_pass), 
                new org.web3j.abi.datatypes.Address(checkAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> compareStrings(String a, String b) {
        final Function function = new Function(FUNC_COMPARESTRINGS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(a), 
                new org.web3j.abi.datatypes.Utf8String(b)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> approveAndCall(String _spender, BigInteger _value, byte[] _extraData) {
        final Function function = new Function(
                FUNC_APPROVEANDCALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.web3j.abi.datatypes.DynamicBytes(_extraData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> appendString(String appendThis) {
        final Function function = new Function(
                FUNC_APPENDSTRING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(appendThis)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> allowance(String _owner, String _spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner), 
                new org.web3j.abi.datatypes.Address(_spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> validLoanAddress(String checkVal) {
        final Function function = new Function(FUNC_VALIDLOANADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(checkVal)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    @Deprecated
    public static MonetaryToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MonetaryToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MonetaryToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MonetaryToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MonetaryToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MonetaryToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MonetaryToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MonetaryToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MonetaryToken> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MonetaryToken.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<MonetaryToken> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MonetaryToken.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MonetaryToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MonetaryToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MonetaryToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MonetaryToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class TransferEventResponse {
        public Log log;

        public String _from;

        public String _to;

        public BigInteger _value;
    }

    public static class ApprovalEventResponse {
        public Log log;

        public String _owner;

        public String _spender;

        public BigInteger _value;
    }
}
