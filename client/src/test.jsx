import axios from "axios"

export default function Test(){

    const testApi = async () =>{
        console.log("버튼클릭");
        const page = 1;
        const newCategory = 0;
        const result = await axios.post("http://localhost:8080/api/trending",{
            page,
            newCategory
        });

        console.log(result);
    }

    const getTotalPage = async () => {
        console.log("TotalPage Button Click");
        const result = await axios.post("http://localhost:8080/api/totalPage");
        console.log("TotalPage", result);
    }

        return(
        <div className="Container">
            <button className="testClick" onClick={testApi}>GetTrending</button>
            <button className="totalPageClick" onClick={getTotalPage}>GetTotalPage</button>
        </div>
    )
}