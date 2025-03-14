
const formatNumber = (number) => {
    return new Intl.NumberFormat("ko-KR", {
        notation : "compact",
        maximumFractionDigits : 1,
    }).format(number);

}

export default formatNumber;