import axios from "axios"

export default function Test(){

    const testApi = async () =>{
        console.log("버튼클릭");
        const result = await axios.post("http://localhost:8080/api/test");

        console.log(result);
    }

        return(
        <div className="Container">
            <button className="testClick" onClick={testApi}>버튼</button>
        </div>
    )
}